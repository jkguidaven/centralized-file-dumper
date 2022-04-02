package state.custom;

import state.AUAppStateMachine;
import state.StateObject;

import static state.StateMap.*;
import static state.predefine.StateMapBase.STATE_NULL;



public class SMIdle extends AUAppStateMachine  {

	public StateObject initialize() {
		setTimeOut(co.idle_duration_upload);
		return returnState(STATE_NULL);
	}


	public StateObject timeout(Object...varargs){
		return returnState(SMCHECKNEWFILE);
	}

	public void uninitialize() { 
	}
	
}
