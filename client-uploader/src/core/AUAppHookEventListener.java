package core;

import state.SMEventCode;
import state.StateMap;
import state.StateObject;
import hook.HookEvent;
import hook.observer.HookEventObserver;


import static core.StateTraceAgent.*;

public class AUAppHookEventListener extends HookEventObserver<StateObject,SMEventCode> {

	public void processEvent(HookEvent<SMEventCode> event){
		Trace(L1,"Hook event detected, processing event..");
		
		
		Trace(L1,"notifying current handler [" + StateMap.getCurrentHookEventHandler().getClass().getSimpleName() + "]");
		notifyHandler( StateMap.getCurrentHookEventHandler(),event);
	}
}
