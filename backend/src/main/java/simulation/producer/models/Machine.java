package simulation.producer.models;

import simulation.producer.models.observer.Subject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Machine extends Subject implements Runnable{

    private static int count = 0;
    private static Color defaultColor;
    
    private int id;
    private String x;
    private String y;

    private List<Queue> observerList = new ArrayList<>();
    private Queue outQueue;
    private Color currentColor;
    private int serviceTime;
    private boolean state = true;

    public Machine(String x, String y){
        this.id = count++;
        this.x = x;
        this.y = y;
        this.serviceTime = (new Random()).nextInt(2, 8)*1000;
    }

    public String getX(){
        return this.x;
    }

    public void setX(String x){
        this.x = x;
    }
    
    public String getY(){
        return this.y;
    }

    public void setY(String y){
        this.y = y;
    }


    public List<Queue> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<Queue> observerList) {
        this.observerList = observerList;
    }

    public Queue getOutQueue() {
        return outQueue;
    }

    public void setOutQueue(Queue outQueue) {
        this.outQueue = outQueue;
    }

    public int getId() {
        return id;
    }


    public Color getDefaultColor() {
        return defaultColor;
    }

    // public void setDefaultColor(Color defaultColor) {
    //     defaultColor = defaultColor;
    // }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean isReady() {
        return this.state;
    }


    public void attach(Queue addQueue){
        observerList.add(addQueue);
    }

    public void detach(Queue removeQueue){
        observerList.remove(removeQueue);
    }

    public synchronized void process(Product currentProduct){
        // this.state = false;
        this.currentColor = currentProduct.getColor();
        // Random randtime = new Random();
        // this.serviceTime=randtime.nextInt(2,10)*1000;
        try {
            System.out.println("Machine "+this.id+" is processing product "+currentProduct.getId()+" for "+this.serviceTime+" ms");
            Thread.sleep(this.serviceTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //send prcessed product to next queue
        outQueue.addProduct(currentProduct);
        
        this.currentColor = this.defaultColor;
        // this.state = true;
    }

    public void notifyObservers() {
        for (Queue observer : observerList) {
            System.out.println("Machine "+this.id+" is notifying queue "+observer.getId());
            observer.update(this);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                notifyObservers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return count == machine.count && id == machine.id && serviceTime == machine.serviceTime && Objects.equals(observerList, machine.observerList) && Objects.equals(outQueue, machine.outQueue) && Objects.equals(defaultColor, machine.defaultColor) && Objects.equals(currentColor, machine.currentColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, observerList, outQueue, id, defaultColor, currentColor, serviceTime);
    }

    @Override
    public String toString() {
        return "Machine{" +
                "count=" + count +
                ", observerList=" + observerList +
                ", outQueue=" + outQueue +
                ", id=" + id +
                ", defaultColor=" + defaultColor +
                ", currentColor=" + currentColor +
                ", serviceTime=" + serviceTime +
                '}';
    }
}
