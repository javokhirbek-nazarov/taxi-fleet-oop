package taxi.fleet.oop.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import taxi.fleet.oop.taxi.Taxi;
import taxi.fleet.oop.taxi.TaxiStatus;

class BookingTest {

    @Test
    void takeNew() {
        Booking booking = new Booking("B1", "Test User");
        Taxi taxi = Mockito.mock(Taxi.class);

        var took = booking.take(taxi);

        Assertions.assertTrue(took);
        assertEquals(BookingStatus.TAKEN, booking.getStatus());
    }

    @Test
    void takeTaken() {
        Booking booking = new Booking("B1", "Test User");
        Taxi taxi1 = Mockito.mock(Taxi.class);
        Taxi taxi2 = Mockito.mock(Taxi.class);
        booking.take(taxi1);

        var took = booking.take(taxi2);

        Assertions.assertFalse(took);
        assertTrue(booking.getTaxi().isPresent());
        assertEquals(taxi1, booking.getTaxi().get());
    }

    @Test
    void takeCompleted() {
        Booking booking = new Booking("B1", "Test User");
        Taxi taxi1 = Mockito.mock(Taxi.class);
        booking.complete();

        assertThrows(IllegalStateException.class, () -> booking.take(taxi1));
        assertTrue(booking.getTaxi().isEmpty());
    }

    @Test
    void completeNew() {
        Booking booking = new Booking("B1", "Test User");

        booking.complete();

        assertEquals(BookingStatus.COMPLETE, booking.getStatus());
        assertTrue(booking.getTaxi().isEmpty());
    }

    @Test
    void completeTaken() {
        Booking booking = new Booking("B1", "Test User");
        Taxi taxi = Mockito.mock(Taxi.class);
        booking.take(taxi);

        booking.complete();

        assertEquals(BookingStatus.COMPLETE, booking.getStatus());
        assertNotNull(booking.getTaxi());
        verify(taxi).setStatus(TaxiStatus.AVAILABLE);
    }

    @Test
    void completeCompleted() {
        Booking booking = new Booking("B1", "Test User");
        booking.complete();

        booking.complete();

        assertEquals(BookingStatus.COMPLETE, booking.getStatus());
    }

    @Test
    void cancelNew() {
        Booking booking = new Booking("B1", "Test User");
        assertThrows(IllegalStateException.class, booking::cancel);
    }

    @Test
    void cancelTaken() {
        Booking booking = new Booking("B1", "Test User");
        Taxi taxi = Mockito.mock(Taxi.class);
        booking.take(taxi);

        booking.cancel();

        verify(taxi).setStatus(TaxiStatus.AVAILABLE);
        Assertions.assertTrue(booking.getTaxi().isEmpty());
        Assertions.assertEquals(BookingStatus.NEW, booking.getStatus());
    }

    @Test
    void cancelCompleted() {
        Booking booking = new Booking("B1", "Test User");
        booking.complete();

        assertThrows(IllegalStateException.class, booking::cancel);
    }
}