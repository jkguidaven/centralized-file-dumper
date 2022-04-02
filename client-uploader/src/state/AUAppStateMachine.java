package state;

import static state.predefine.StateMapBase.STATE_NULL;
import static state.StateMap.RegisterAUAppStateMachine;
import core.AUAppConfigObj;
import core.AUAppHTTPCOMObj;
import hook.HookEvent;
import hook.observer.HookEventHandler;


import static core.StateTraceAgent.*;


public abstract class AUAppStateMachine extends StateMachine implements HookEventHandler<StateObject,SMEventCode> {
	protected static AUAppHTTPCOMObj HTTPCOM = AUAppHTTPCOMObj.getInstance();
	protected static AUAppConfigObj co = AUAppConfigObj.getInstance();
	

	protected static long cache_lastmodified_time = 0;
	
	protected static String strCurrentErrorMsg = "";
	
	public AUAppStateMachine(){ 
		RegisterAUAppStateMachine(this);
	}
		
	public StateObject processEvent(HookEvent<SMEventCode> event) {
		
		
		if(event == null){
			Trace(L2,"received null event. ignore event...");
			return returnState(STATE_NULL);
		}
		else{
			switch(event.getCode()){
	
			}
		}
			
			
		Trace(L2,"ignore event!!");
		return returnState(STATE_NULL);
	}

	public final void handleProcessEvent(StateObject pret) {
			StateMap.getStateContext().setNextState(pret);
	}

	
	public void StateIdle()  {
		// let thread sleep
		try {
			Thread.sleep(co.idle_duration_cpu);
		} catch (InterruptedException e) {}
		Trace(L6,"StateIdle(" + this.getClass().getSimpleName() + ")");
	}
}
