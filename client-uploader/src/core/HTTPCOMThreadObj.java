package core;

import core.AUAppHTTPCOMObj.HTTPCOMRequest;

public class HTTPCOMThreadObj implements Runnable {
	private AUAppHTTPCOMObj HTTPCOM = null;
	private HTTPCOMRequest  request = null;
	private Object		param  = null;
	public HTTPCOMThreadObj(AUAppHTTPCOMObj HTTPCOM, HTTPCOMRequest request,Object param){
		this.HTTPCOM = HTTPCOM;
		this.request = request;
		this.param = param; 
	}

	public void run() {
			HTTPCOM.sendRequest(request, param);
	}
}
