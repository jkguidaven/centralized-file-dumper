package state.custom;

import state.AUAppStateMachine;
import state.StateObject;

import static state.StateMap.*;


public class SMInProgress extends AUAppStateMachine {

	
	public StateObject initialize() {
		this.setTimeOut(10000);
		return returnState(STATE_NULL);
	}

	public void uninitialize() { }

}
