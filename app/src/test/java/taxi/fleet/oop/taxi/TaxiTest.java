package taxi.fleet.oop.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.fleet.oop.booking.Booking;
import taxi.fleet.oop.booking.BookingStatus;
import taxi.fleet.oop.observer.Observable;

class TaxiTest {

    private Taxi taxi;
    private Observable<Booking> bookingCenter;
    private Booking booking;

    @BeforeEach
    void setUp() {
        bookingCenter = mock(Observable.class);
        booking = mock(Booking.class);
        taxi = new Taxi("taxi1", "LocationA", bookingCenter);
    }

    @Test
    void taxiInitialization() {
        assertEquals("taxi1", taxi.getId());
        assertEquals("LocationA", taxi.getLocation());
        assertEquals(TaxiStatus.AVAILABLE, taxi.getStatus());
    }

    @Test
    void setStatusToAvailable() {
        taxi.setStatus(TaxiStatus.AVAILABLE);

        verify(bookingCenter, times(2)).subscribe(taxi);
        assertEquals(TaxiStatus.AVAILABLE, taxi.getStatus());
    }

    @Test
    void setStatusToBooked() {
        taxi.setStatus(TaxiStatus.BOOKED);

        verify(bookingCenter, times(1)).unSubscribe(taxi);
        assertEquals(TaxiStatus.BOOKED, taxi.getStatus());
    }

    @Test
    void testUpdateWithNewBooking() throws InterruptedException {
        when(booking.getStatus()).thenReturn(BookingStatus.NEW);
        when(booking.take(taxi)).thenReturn(true);

        taxi.update(booking);

        assertEquals(TaxiStatus.BOOKED, taxi.getStatus());
        verify(bookingCenter).unSubscribe(taxi);
        verify(booking, times(1)).take(taxi);
    }

    @Test
    void testUpdateWhenAlreadyBookedThrowsException() {
        taxi.setStatus(TaxiStatus.BOOKED);

        when(booking.getStatus()).thenReturn(BookingStatus.NEW);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            taxi.update(booking);
        });
        assertEquals("Can not be updated with booking when already booked", exception.getMessage());
    }

    @Test
    void testUpdateWithNonNewBooking() throws InterruptedException {
        when(booking.getStatus()).thenReturn(BookingStatus.COMPLETE);

        taxi.update(booking);

        assertEquals(TaxiStatus.AVAILABLE, taxi.getStatus());
        verify(booking, times(1)).take(any(Taxi.class));
    }
}