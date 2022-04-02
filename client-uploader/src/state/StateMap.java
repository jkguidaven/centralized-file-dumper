package state;

import static agent.base.TraceAgentBase.L2;
import static core.StateTraceAgent.Trace;
import hook.observer.HookEventHandler;
import state.custom.SMCheckNewFile;
import state.custom.SMExit;
import state.custom.SMIdle;
import state.custom.SMInProgress;
import state.custom.SMLoadConfig;
import state.custom.SMNoResponse;
import state.custom.SMOnError;
import state.custom.SMUploading;
import state.predefine.StateMapBase;

@SuppressWarnings("rawtypes")
public class StateMap extends StateMapBase {

	
	private static final AUAppSMList registeredSMList =new AUAppSMList();
	
	public static final Class SMLOADCONFIG 		= new SMLoadConfig().getClass();
	public static final Class SMCHECKNEWFILE 	= new SMCheckNewFile().getClass();
	public static final Class SMUPLOADING  		= new SMUploading().getClass();
	public static final Class SMINPROGRESS 		= new SMInProgress().getClass();
	public static final Class SMNORESPONSE 		= new SMNoResponse().getClass();
	public static final Class SMONERROR 		= new SMOnError().getClass();
	public static final Class SMIDLE 			= new SMIdle().getClass();
	public static final Class SMEXIT 			= new SMExit().getClass();
	
	private StateMap(){
		Trace(L2,"Initializing StateMap....");
	}
	
	
	public static void RegisterAUAppStateMachine(AUAppStateMachine state){
		Trace(L2,"Registering AUApp State machine:" + state.getClass().getSimpleName());
		registeredSMList.registerAUAppStateMachine(state);
	}
	
	public static HookEventHandler<StateObject,SMEventCode> getCurrentHookEventHandler(){
		return registeredSMList.getCurrentSM();
	}
}
