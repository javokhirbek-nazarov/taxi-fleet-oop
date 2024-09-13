package taxi.fleet.oop.booking;

import java.util.Optional;
import taxi.fleet.oop.taxi.Taxi;

public class Booking {

    private final String id;

    private final String client;

    private Taxi taxi;

    private BookingState state;

    private final BookingState newState;

    private final BookingState takenState;

    private final BookingState completeState;

    public Booking(String id, String client) {
        this.id = id;
        this.client = client;
        this.newState = new NewState(this);
        this.takenState = new TakenState(this);
        this.completeState = new CompleteState(this);
        this.state = this.newState;
    }

    public synchronized boolean take(Taxi taxi) {
        System.out.println("Trying to take a booking with id " + getId());
        return state.take(taxi);
    }

    public void complete() {
        System.out.println("Trying to complete a booking with id " + getId());
        state.complete();
    }

    public void cancel() {
        System.out.println("Trying to  cancel a booking with id " + getId());
        state.cancel();
    }

    public String getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public Optional<Taxi> getTaxi() {
        return Optional.ofNullable(taxi);
    }

    public BookingStatus getStatus() {
        return state.getStatus();
    }

    void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    void setState(BookingState state) {
        this.state = state;
    }

    BookingState getNewState() {
        return newState;
    }

    BookingState getTakenState() {
        return takenState;
    }

    BookingState getCompleteState() {
        return completeState;
    }
}
