package taxi.fleet.oop.booking;

import taxi.fleet.oop.taxi.Taxi;

class NewState implements BookingState {

    private final Booking booking;

    public NewState(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean take(Taxi taxi) {
        booking.setTaxi(taxi);
        booking.setState(booking.getTakenState());
        System.out.println("Taxi (Id = " + taxi.getId() + ") took booking (Id = " + booking.getId() + ")\n");
        return true;
    }

    @Override
    public void complete() {
        booking.setState(booking.getCompleteState());
    }

    @Override
    public void cancel() {
        throw new IllegalStateException("Can not cancel new booking");
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.NEW;
    }
}
