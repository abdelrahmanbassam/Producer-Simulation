package simulation.producer.models;

import simulation.producer.models.observer.Subject;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Machine  implements Runnable{

    private int count=0;

    private List<Queue> observerList=new ArrayList<>();
    private Queue outQueue;
    private int id;
    private Color defaultColor;
    private Color currentColor;
    private int serviceTime;
//    private boolean state;

    public Machine(){
        this.id = count++;
        Random randcol = new Random();
        this.currentColor = new Color(randcol.nextInt(256), randcol.nextInt(256), randcol.nextInt(256));
    }

    public Machine(Color color){
        this.id = count++;
        this.defaultColor = color;
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

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

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

//    public boolean isState() {
//        return state;
//    }
//
//    public void setReady(boolean ready) {
//        state = ready;
//    }


    public void attach(Queue addQueue){
        observerList.add(addQueue);
    }

    public void detach(Queue removeQueue){
        observerList.remove(removeQueue);
    }

    public void process(Product currentProduct){

//        this.currentColor=currentProduct.getcolor();

        Random randtime = new Random();
        this.serviceTime=randtime.nextInt(2,10);
        this.serviceTime=this.serviceTime*1000;
        try {
            Thread.sleep(this.serviceTime);
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

//        outQueue.addproduct(currentProduct);
        notifyObservers();

    }

    public void notifyObservers() {
        for (Queue observer : observerList) {
//            observer.update(this);
        }
    }

    @Override
    public void run() {
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
