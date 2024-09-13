package taxi.fleet.oop.observer;

public interface Observer<T> {

    void update(T arg) throws InterruptedException;
}
