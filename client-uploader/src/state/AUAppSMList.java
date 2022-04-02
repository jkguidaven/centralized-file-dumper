package state;

import java.util.ArrayList;

public class AUAppSMList {
	public ArrayList<AUAppStateMachine> list;
	
	public AUAppSMList(){
		list = new ArrayList<AUAppStateMachine>();
	}
	
	public void registerAUAppStateMachine(AUAppStateMachine state){
		list.add(state);
	}
	
	public AUAppStateMachine getCurrentSM(){
		int index = 0;
		
		for(AUAppStateMachine ref : list){
			if(ref == StateMap.getStateContext().getCurrentState()){
				if(index != 0){
					swap(0,index);
				}
				
				return ref;
			}
		}
		
		return null;
	}
	
	private void swap(int index,int index2){
		AUAppStateMachine temp = list.get(index);
		list.set(index,list.get(index2));
		list.set(index2, temp);
	}
}
