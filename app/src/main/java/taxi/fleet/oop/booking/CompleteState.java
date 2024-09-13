package taxi.fleet.oop.booking;

import taxi.fleet.oop.taxi.Taxi;

class CompleteState implements BookingState {

    private final Booking booking;

    public CompleteState(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean take(Taxi taxi) {
        throw new IllegalStateException("Can't take a complete booking id " + booking.getId());
    }

    @Override
    public void complete() {
        System.out.println("Booking with id " + booking.getId() + " is already complete");
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Can't cancel complete booking with id " + booking.getId());
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.COMPLETE;
    }
}
