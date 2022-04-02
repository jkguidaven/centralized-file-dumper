package state.custom;

import state.AUAppStateMachine;
import state.StateObject;

import static state.StateMap.*;

public class SMNoResponse extends AUAppStateMachine  {

	
	public StateObject initialize() {
		return returnState(STATE_NULL);
	}

	public void uninitialize() { }
}
