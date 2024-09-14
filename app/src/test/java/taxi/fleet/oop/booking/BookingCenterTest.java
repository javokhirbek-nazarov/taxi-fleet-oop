package taxi.fleet.oop.booking;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.fleet.oop.observer.Observer;

class BookingCenterTest {

    private BookingCenter bookingCenter;
    private Observer<Booking> observer1;
    private Observer<Booking> observer2;

    @BeforeEach
    void setUp() {
        bookingCenter = new BookingCenter();
        observer1 = mock(Observer.class);
        observer2 = mock(Observer.class);
    }

    @Test
    void subscribeAndUnSubscribe() throws InterruptedException {
        bookingCenter.subscribe(observer1);
        bookingCenter.subscribe(observer2);

        assertDoesNotThrow(() -> bookingCenter.newBooking("1", "Client A"));

        verify(observer1, times(1)).update(any(Booking.class));
        verify(observer2, times(1)).update(any(Booking.class));

        bookingCenter.unSubscribe(observer1);

        assertDoesNotThrow(() -> bookingCenter.newBooking("2", "Client B"));

        verify(observer1, times(1)).update(any(Booking.class));
        verify(observer2, times(2)).update(any(Booking.class));
    }

    @Test
    void getStatisticsWithNoBookings() {
        BookingStatistics stats = bookingCenter.getStatistics();

        assertEquals(0, stats.getNewCount());
        assertEquals(0, stats.getTakenCount());
        assertEquals(0, stats.getCompleteCount());
        assertEquals(0, stats.getTotalCount());
    }

    @Test
    void getStatisticsWithBookings() throws InterruptedException {
        bookingCenter.newBooking("1", "Client A");
        bookingCenter.newBooking("2", "Client B");
        bookingCenter.newBooking("3", "Client C");

        BookingStatistics stats = bookingCenter.getStatistics();

        assertEquals(3, stats.getNewCount());
        assertEquals(0, stats.getTakenCount());
        assertEquals(0, stats.getCompleteCount());
        assertEquals(3, stats.getTotalCount());
    }
}
