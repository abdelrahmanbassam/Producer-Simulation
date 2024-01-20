package simulation.producer.services;

import java.util.ArrayList;

import simulation.producer.models.Machine;
import simulation.producer.models.Queue;

public class QueueManager {
    
    public ArrayList<Queue> addQueue(String x, String y, String defaultColor) {
        SimulationManager.getInstance().queues.add(new Queue(x, y, defaultColor));
        return SimulationManager.getInstance().queues;
    }

    public void connectQueueToMachine(int queueId, int machineId) {
        for(Machine machine : SimulationManager.getInstance().machines){
            if(machine.getId() == machineId)
            for(Queue queue : SimulationManager.getInstance().queues){
                if(queue.getId() == queueId){
                    machine.attach(queue);
                    return;
                }
            }
        }
    }  
    
    // public void removeQueue(int queueId) {
    //     for(Queue queue : queues){
    //         if(queue.getId() == queueId){
    //             queues.remove(queue);
    //             return;
    //         }
    //     }
    // }

}
