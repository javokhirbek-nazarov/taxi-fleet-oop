package taxi.fleet.oop.dashboard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import taxi.fleet.oop.booking.BookingCenter;
import taxi.fleet.oop.booking.BookingStatistics;

class BookingDashboardTest {

    @Test
    void getStatistics() {
        BookingCenter bookingCenter = mock(BookingCenter.class);
        BookingDashboard bookingDashboard = new BookingDashboard(bookingCenter);
        when(bookingCenter.getStatistics()).thenReturn(new BookingStatistics());

        bookingDashboard.getStatistics();

        verify(bookingCenter).getStatistics();
    }
}