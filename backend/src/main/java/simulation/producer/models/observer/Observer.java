package simulation.producer.models.observer;

public abstract class Observer {
    private Subject subject;
    public abstract void update();
    
    public Observer (Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }
}
