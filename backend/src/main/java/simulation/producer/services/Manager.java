package simulation.producer.services;

import java.awt.List;
import java.util.ArrayList;

import simulation.producer.models.Machine;
import simulation.producer.models.Product;
import simulation.producer.models.Queue;

public class Manager {
    ArrayList<Queue> queues = new ArrayList<Queue>();
    ArrayList<Machine> machines = new ArrayList<Machine>();
    ArrayList<Product> products = new ArrayList<Product>();


    //start simulation
    public void start() {
        for(Machine machine : machines){
            Thread thread = new Thread(machine);
            thread.start();
        }
    }



    public void connectMAchineToQueue(int machineId, int queueId) {
        for(Machine machine : machines){
            if(machine.getId() == machineId)
            for(Queue queue : queues){
                if(queue.getId() == queueId){
                    machine.attach(queue);
                    return;
                }
            }
        }
    }

    public void connectQueueToMachine(int machineId, int queueId) {
        for(Machine machine : machines){
            if(machine.getId() == machineId)
            for(Queue queue : queues){
                if(queue.getId() == queueId){
                    machine.setOutQueue(queue);
                    return;
                }
            }
        }
    }   

    public ArrayList<Queue> addQueue(String x, String y) {
        queues.add(new Queue(x, y));
        return queues;
    }

    public ArrayList<Machine> addMachine(String x, String y) {
        machines.add(new Machine(x, y));
        return machines;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    // public void removeQueue(int queueId) {
    //     for(Queue queue : queues){
    //         if(queue.getId() == queueId){
    //             queues.remove(queue);
    //             return;
    //         }
    //     }
    // }

    // public void removeMachine(int machineId) {
    //     for(Machine machine : machines){
    //         if(machine.getId() == machineId){
    //             machines.remove(machine);
    //             return;
    //         }
    //     }
    // }   

    // public void removeProduct(int productId) {
    //     for(Product product : products){
    //         if(product.getId() == productId){
    //             products.remove(product);
    //             return;
    //         }
    //     }
    // }





    public static void main(String[] args) {
        Queue q0 = new Queue("", "");
        Queue q1 = new Queue("", "");
        Machine m0 = new Machine("", "");
        Machine m1 = new Machine("", "");

        for (int i = 0; i < 10; i++) {
            q0.addProduct(new Product());
        }

        m0.attach(q0);
        m1.attach(q0);
        
        m0.setOutQueue(q1);
        m1.setOutQueue(q1);

        Thread m0Thread = new Thread(m0);
        Thread m1Thread = new Thread(m1);

        m0Thread.start();
        m1Thread.start();

        //pause m0Thread for 5 seconds
        

        
    }
}
