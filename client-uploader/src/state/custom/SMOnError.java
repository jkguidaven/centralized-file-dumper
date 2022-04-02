package state.custom;

import static state.predefine.StateMapBase.STATE_NULL;
import state.AUAppStateMachine;
import state.StateObject;

public class SMOnError extends AUAppStateMachine {
	public StateObject initialize() {
		return returnState(STATE_NULL);
	}

	public void uninitialize() { }
}
