package core;


import static state.StateMachine.returnState;
import static state.StateMap.*;
import hook.observer.HookEventObserver;
import state.SMEventCode;
import state.StateObject;


import static core.StateTraceAgent.*;

public class AUAppStateContext extends StateContext  {
	private  AUAppHookEventListener  HookEventListener = null;

	public AUAppStateContext() {
		super(returnState(SMLOADCONFIG), // begin state declare
			  returnState(SMEXIT)); // end state declare
		
		Trace(L1,"Initializing Hook Event Listener instance..");
		HookEventListener = new AUAppHookEventListener();
		

		Trace(L1,"Initializing HTTP COM Object instance..");
		AUAppHTTPCOMObj.getInstance();
		
		Trace(L1,"Adding Hook to HTTP COM Object Event");
		AUAppHTTPCOMObj.getInstance().registerObserver(getObserver());
		
	}
	
	

	public HookEventObserver<StateObject,SMEventCode> getObserver(){
		return HookEventListener;
	}
}
