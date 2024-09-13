package taxi.fleet.oop.booking;

import taxi.fleet.oop.taxi.Taxi;

public interface BookingState {

    boolean take(Taxi taxi);

    void complete();

    void cancel();

    BookingStatus getStatus();
}
