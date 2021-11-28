package cloudsim.ext.datacenter;

import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
//
    
public class PSOBalancer extends VmLoadBalancer {
    	
	private Map<Integer, VirtualMachineState> vmStatesList;
	private int currVm = -1;

	public PSOBalancer(Map<Integer, VirtualMachineState> vmStatesList){
		super();
		
		this.vmStatesList = vmStatesList;
	}
	
	public int getNextAvailableVm(){
		currVm++;
		DatacenterController.SPECIAL_VAR=2;
                
                  int PSONum = 100;
        int searchTime = 1000;
        PSO pso = new PSO(PSONum);
        int funDim = Function.getDim();
                
		if (currVm >= vmStatesList.size()){
			currVm = 0;
		}
		
		allocatedVm(currVm);
		
		return currVm;
		
	}
}
