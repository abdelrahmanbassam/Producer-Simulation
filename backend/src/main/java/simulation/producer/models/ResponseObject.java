package simulation.producer.models;

import java.util.ArrayList;

public class ResponseObject {
    private ArrayList<Machine> machines;
    private ArrayList<Queue> queues;

    public ResponseObject(ArrayList<Machine> machines, ArrayList<Queue> queues) {
        this.machines = machines;
        this.queues = queues;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public ArrayList<Queue> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<Queue> queues) {
        this.queues = queues;
    }
}
