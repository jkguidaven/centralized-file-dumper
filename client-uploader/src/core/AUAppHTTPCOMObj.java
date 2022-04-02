package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import state.SMEventCode;
import state.StateObject;
import hook.HookEvent;
import hook.source.HookEventSubject;
import static core.StateTraceAgent.*;
public class AUAppHTTPCOMObj extends HookEventSubject<StateObject,SMEventCode> {
	private static AUAppConfigObj co = AUAppConfigObj.getInstance();
	private CloseableHttpClient client = null;

	public static enum HTTPCOMRequest { 
		SEND_LIST(co.server_check) ,
		SEND_FILE(co.server_upload);
		
		private String handle = null;

	    private HTTPCOMRequest(final String handle) {
	    	this.handle = handle;
	    }
	    
	    public void setHandle(String handle){
	    	Trace(L3,"+" + toString() + "::setHandle(" + handle + ")");
	    	this.handle = handle;
	    	Trace(L3,"-" + toString() + "::setHandle(" + handle + ")");
	    }
	    public String getHandle(){
	    	return handle;
	    }
	}
	
	public void recompileHTTPCOMRequest(){
		Trace(L2,"recompiling HTTPCOM request type with new handle.");
		HTTPCOMRequest.SEND_LIST.setHandle(co.server_check);
		
		Trace(L2,"done compiling.");
	}
	
	private static AUAppHTTPCOMObj _instance = null;
	private AUAppHTTPCOMObj(){
		client = HttpClients.createDefault();
	}
	
	public static AUAppHTTPCOMObj getInstance(){
		
		if(_instance == null)
			_instance = new AUAppHTTPCOMObj();
		
		
		return _instance;
	}
	
	
	public void processHTTPCOMRequest(HTTPCOMRequest request,Object param) {
		Trace(L2,"processing new HTTP Request[" + request + "], handle = " + request.getHandle());

		Trace(L3,"generating HTTPCOM thead..");
		HTTPCOMThreadObj threadHandle = new HTTPCOMThreadObj(this,request,param);
		Trace(L3,"thread begin..");
		(new Thread(threadHandle)).start();
	}
	
	public void sendRequest(HTTPCOMRequest request,Object param){
		HttpUriRequest HTTPrequest = translateRequest(request,param);
		if(HTTPrequest == null){
			Trace(L2,"notifying observer of aborted request..");
			HookEvent<SMEventCode> event = new HookEvent<SMEventCode>(SMEventCode.ABORTED_HTTP_REQUEST);
		
			notify( event );
			return;
		}
		
		Trace(L2,"sending request to destination[" + HTTPrequest.getURI() + "]");
		try {
			Object response = translateResponse(client.execute(HTTPrequest));
			notify(processResponse(response));
		} catch (IOException e) {
			Trace(L2,"unexpected exception was caught while request is being send,e=" + e.getMessage());
			
		}finally{

			if(HTTPrequest != null){
				Trace(L2,"Releasing HTTP request....");
				HTTPrequest.abort();
			}
		}
	}
	
	private Object translateResponse(HttpResponse response){
		Trace(L3,"translating HTTP response[" +response.getStatusLine() +"]..");
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = null;
			while ((line = reader.readLine()) != null) 
			        sb.append(line + "\n");
			
			Trace(L3,"line =" + sb.toString());
			
		} catch (IllegalStateException | IOException e) {
			Trace(L3,"unexpected exception was caught while reading response body!,e.Msg= " + e.getMessage());
		}
		finally{
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private HttpUriRequest translateRequest(HTTPCOMRequest request,Object param){
		Trace(L3,"translating HTTP request..");

		String destination = "http"+ ( co.SecureEnabled ? "s://" : "://") + co.server_domain + "/" + request.getHandle();
		HttpUriRequest HTTPRequest = co.server_method_post ? new HttpPost(destination) : new HttpGet(destination);
		Trace(L3,"generating HTTP request[method=" + HTTPRequest.getMethod() + ";action=" + HTTPRequest.getURI() +"]");
		HttpEntity entity = null;
		
		try
		{
			switch(request){
			
				case SEND_FILE:
				File file = (File)param;
				Trace(L3,"param instance of File.. building multipart HTTP entity");
				MultipartEntityBuilder  mpbuilder = MultipartEntityBuilder.create();
				Trace(L3,"adding Text Body: branch_code = " + co.branch_code);
				mpbuilder.addTextBody("branch_code", co.branch_code);
				Trace(L3,"adding Text Body: file_size = " + file.length());
				mpbuilder.addTextBody("file_size", Long.toString(file.length()));
				Trace(L3,"adding Text Body: file_name = " + file.getName());
				mpbuilder.addTextBody("file_name", file.getName());
				Trace(L3,"adding raw: file = " + file);
				FileBody fileBody = new FileBody(file);
				mpbuilder.addPart("file", fileBody);
				Trace(L3,"building entity from builder..");
				entity = mpbuilder.build();
				break;
			
				case SEND_LIST:
				Trace(L3,"param instance of String[][].. packaging list to HTTP array data..");
				ArrayList<String[]> file_list = (ArrayList<String[]>)param;
				List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
				for(String[] file_data : file_list){
					String json_str = "{\"filename\":\"" + file_data[0] + "\",\"size\":" + file_data[1] + "}";
					Trace(L3,"Adding value pair[ file[] = " + json_str +" ]");
					nameValuePairs.add(new BasicNameValuePair("file[]", json_str));
				}
				
				entity = new UrlEncodedFormEntity (nameValuePairs,"UTF-8");
				break;
			}
		}catch( ClassCastException ccex){
			Trace(L3,"error on casting parameter[e.msg=" + ccex.getMessage()+ "]");
			Trace(L3,"Aborting HTTP request..");
			return null;
		} catch (UnsupportedEncodingException e) {
			Trace(L3,"error on encoding parameter[e.msg=" + e.getMessage()+ "]");
		}
		
		if(co.server_method_post)
			((HttpPost)HTTPRequest).setEntity(entity);
		else
			((HttpPost)HTTPRequest).setEntity(entity);
			
		return HTTPRequest;
		
	}
	
	
	
	
	private HookEvent<SMEventCode> processResponse(Object response){
		
		
		return null;
	}
	
	
}
