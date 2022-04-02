package state.custom;

import java.io.File;
import java.util.ArrayList;

import core.AUAppHTTPCOMObj.HTTPCOMRequest;

import state.AUAppStateMachine;
import state.StateObject;

import static state.StateMap.*;


import static core.StateTraceAgent.*;
public class SMCheckNewFile extends AUAppStateMachine {

	
	public StateObject initialize() {
		
		Trace(L2,"get file list from path:" + co.transfer_path );
		File dir = new File(co.transfer_path);
		
		
		if(!dir.exists()){
			String msg = "Transfer folder does not exist!";
			Trace(L2," path is not existing!!, going to SMOnError state");
			Trace(L2,"Error msg= " + msg);
			return returnState(SMONERROR);
		}
		
		Trace(L2,"checking files last modified...");
		if(cache_lastmodified_time == dir.getAbsoluteFile().lastModified()){
			Trace(L2,"no files modified detected...");
			return returnState(SMIDLE);
		}
		
		ArrayList<String[]> filesData = new ArrayList<String[]>();
		
		Trace(L2,"packing file data into String array");
		for(File file : dir.listFiles()){
			filesData.add( new String[] {  file.getName() , Long.toString(file.length()) });
			Trace(L2,"filename=" + file.getName() + ";size=" + Long.toString(file.length()));
		}
		HTTPCOM.processHTTPCOMRequest(HTTPCOMRequest.SEND_LIST,filesData);
		return returnState(SMINPROGRESS);
	}

	public void uninitialize() { }
}
