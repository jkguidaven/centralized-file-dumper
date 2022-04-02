package state.custom;

import java.io.File;
import java.lang.reflect.Field;

import agent.base.TraceLevelObj;

import core.StateTraceAgent;

import FileLoader.ConfigFileLoader;
import FileLoader.ConfigObjContainer;


import state.AUAppStateMachine;
import state.StateObject;

import static state.StateMap.*;


import static core.StateTraceAgent.*;

public class SMLoadConfig extends AUAppStateMachine  {

	public static final String SYSTEM_CONFIG_FILE = "System.ini";
	

	
	public Object  extractConfig(ConfigObjContainer config,Object defaultValue,String ConfigName){
		Trace(L3,"extracting Config['" + ConfigName + "']");
		String val = config.getVal(ConfigName);
		
		
		if( val != null ){
			@SuppressWarnings("rawtypes")
			Class c = defaultValue.getClass();
			Trace(L3,"Identifying config type as:" + c.getName());
			if(c.equals(String.class)){
				Trace(L2,"good parse[String type], use new value = " + val);
				return val;
			} else if(c.equals(Integer.class)){
				int pret = Integer.parseInt(val); 
				Trace(L2,"good parse[Integer type], use new value = " + pret);
				return pret;
			} else if(c.equals(Long.class)){
				long pret = Long.parseLong(val); 
				Trace(L2,"good parse[Long type], use new value = " + pret);
				return pret;
			} else if(c.equals(Boolean.class)){
				if(val.trim().toLowerCase() == "true" || val.trim().toLowerCase() == "false"){
					Trace(L2,"good parse[Boolean type], use new value = " + val.trim());
					return val.trim().toLowerCase() == "true" ? true : false; 
				}
				else
				{
					Trace(L2,"unable to parse 'boolean' value, use default Value = " + defaultValue);
					return defaultValue;
				}
			}
			

			Trace(L2,"Unable to identify 'type' as valid, use default value = " + defaultValue);
		}
		else
			Trace(L2,"No config value found, use default value = " + defaultValue);
		
		return defaultValue;
	}
	
	
	public StateObject initialize() {
		Trace(L2,"Loading Config file ['" + SYSTEM_CONFIG_FILE + "'],creating instance for File loader!");
		ConfigFileLoader loader = new ConfigFileLoader(new File(SYSTEM_CONFIG_FILE));
		try {
			ConfigObjContainer config = loader.loadFile();
			
			for(Field field : co.getClass().getDeclaredFields() ){
				 if(field.getModifiers() == 1){
					 field.set(co, extractConfig(config,field.get(co),field.getName()));
				 }
			}

			Trace(L2,"Successully loaded config file without critical error!");
			Trace(L2,"Adjusting Trace Agent...");
			StateTraceAgent.getInstance().setTraceLevel(new TraceLevelObj(co.trace_level));
			Trace(L2,"Trace level adjusted to L" + co.trace_level);
			StateTraceAgent.getInstance().reinitialize(co.trace_backup, co.trace_backup_format, co.trace_dump_size);
			Trace(L2,"reinitialize HTTPCOM request data");
			HTTPCOM.recompileHTTPCOMRequest();
			
		} catch (Exception e) {
			Trace(L2,"Error loading config file!!, closing application!!;e = " + e.getMessage());
			Trace(L2,"Exception caught during read!, refer to loader trace file['" + loader.getTrace() + "'] for detailed logs.");
			return returnState(SMEXIT);
		}
		
		return returnState(SMCHECKNEWFILE);
	}

	public void uninitialize() { }

}
