package taxi.fleet.oop.booking;

import taxi.fleet.oop.taxi.Taxi;
import taxi.fleet.oop.taxi.TaxiStatus;

class TakenState implements BookingState {

    private final Booking booking;

    public TakenState(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean take(Taxi taxi) {
        runCheck();
        var taxiId = booking.getTaxi().get().getId();
        System.out.println("Warning already taken by a taxi with id " + taxiId);
        return false;
    }

    @Override
    public void complete() {
        runCheck();
        booking.getTaxi().get().setStatus(TaxiStatus.AVAILABLE);
        booking.setState(booking.getCompleteState());
    }

    @Override
    public void cancel() {
        runCheck();
        booking.getTaxi().get().setStatus(TaxiStatus.AVAILABLE);
        booking.setTaxi(null);
        booking.setState(booking.getNewState());
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.TAKEN;
    }

    private void runCheck() {
        if (booking.getTaxi().isEmpty()) {
            throw new IllegalStateException("Should never happen");
        }
    }
}
