package taxi.fleet.oop.observer;

public interface Observable<T> {

    void updateObservers() throws InterruptedException;

    void subscribe(Observer<T> observer);

    void unSubscribe(Observer<T> observer);
}
