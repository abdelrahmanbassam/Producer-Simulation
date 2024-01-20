package simulation.producer.services;

import java.util.ArrayList;

import simulation.producer.models.Machine;
import simulation.producer.models.Queue;

public class MachineManager {

    public ArrayList<Machine> addMachine(String x, String y, String defaultColor) {
        SimulationManager.getInstance().machines.add(new Machine(x, y, defaultColor));
        return SimulationManager.getInstance().machines;
    }


    public void connectMAchineToQueue(int machineId, int queueId) {
        for(Machine machine : SimulationManager.getInstance().machines){
            if(machine.getId() == machineId)
            for(Queue queue : SimulationManager.getInstance().queues){
                if(queue.getId() == queueId){
                    machine.setOutQueue(queue);
                    return;
                }
            }
        }
    }

    
    // public void removeMachine(int machineId) {
    //     for(Machine machine : machines){
    //         if(machine.getId() == machineId){
    //             machines.remove(machine);
    //             return;
    //         }
    //     }
    // }   
}
