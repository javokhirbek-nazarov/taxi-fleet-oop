package taxi.fleet.oop.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import taxi.fleet.oop.taxi.Taxi;

class NewStateTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking("B1", "John Doe");
    }

    @Test
    void take() {
        NewState newState = new NewState(booking);
        var mockTaxi = Mockito.mock(Taxi.class);

        var took = newState.take(mockTaxi);

        Assertions.assertTrue(took);
        Assertions.assertTrue(booking.getTaxi().isPresent());
        Assertions.assertEquals(mockTaxi, booking.getTaxi().get());
        Assertions.assertEquals(BookingStatus.TAKEN, booking.getStatus());
    }

    @Test
    void complete() {
        NewState newState = new NewState(booking);
        newState.complete();
        Assertions.assertEquals(BookingStatus.COMPLETE, booking.getStatus());
    }

    @Test
    void cancel() {
        NewState newState = new NewState(booking);
        Assertions.assertThrows(IllegalStateException.class, newState::cancel);
    }
}