package cloudsim.ext.datacenter;

import java.util.Map;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
//

public class GABalance extends VmLoadBalancer {

    private Map<Integer, VirtualMachineState> vmStatesList;
    private int currVm = -1;

    public GABalance(Map<Integer, VirtualMachineState> vmStatesList) {
        super();
        this.vmStatesList = vmStatesList;
    }

    
    static int c=0;
    public int getNextAvailableVm() {
        DatacenterController.SPECIAL_VAR = 1;
if(c++==0){
        GA j = new GA();
        j.process();
}
        currVm++;
        if (currVm >= vmStatesList.size()) {
            currVm = 0;
        }
        allocatedVm(currVm);
        return currVm;

    }
}
