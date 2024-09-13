package taxi.fleet.oop.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import taxi.fleet.oop.observer.Observable;
import taxi.fleet.oop.observer.Observer;

public class BookingCenter implements Observable<Booking> {

    private final List<Observer<Booking>> observers = new ArrayList<>();
    private final Map<String, Booking> bookingMap = new ConcurrentHashMap<>();
    private Booking newBooking;

    public BookingCenter() {
    }

    @Override
    public void updateObservers() throws InterruptedException {

        List<Runnable> tasks = observers.stream().map(this::getRunnableForObserver).toList();

        ExecutorService executorService = Executors.newCachedThreadPool();
        tasks.forEach(executorService::submit);

        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
    }

    private Runnable getRunnableForObserver(Observer<Booking> observer) {
        return () -> {
            try {
                observer.update(newBooking);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public void subscribe(Observer<Booking> observer) {
        observers.add(observer);
    }

    @Override
    public void unSubscribe(Observer<Booking> observer) {
        observers.remove(observer);
    }

    public void newBooking(String id, String client) throws InterruptedException {
        newBooking = new Booking(id, client);
        bookingMap.put(newBooking.getId(), newBooking);
        updateObservers();
        newBooking = null;
    }

    public BookingStatistics getStatistics() {

        var bookingStatistics = new BookingStatistics();
        var bookings = bookingMap.values();

        var newCount = bookings.stream().filter(b -> BookingStatus.NEW.equals(b.getStatus()))
            .count();
        bookingStatistics.setNewCount(newCount);

        var takenCount = bookings.stream().filter(b -> BookingStatus.TAKEN.equals(b.getStatus()))
            .count();
        bookingStatistics.setTakenCount(takenCount);

        var completeCount = bookings.stream()
            .filter(b -> BookingStatus.COMPLETE.equals(b.getStatus())).count();
        bookingStatistics.setCompleteCount(completeCount);

        var totalCount = bookings.size();
        bookingStatistics.setTotalCount(totalCount);

        return bookingStatistics;
    }

}

