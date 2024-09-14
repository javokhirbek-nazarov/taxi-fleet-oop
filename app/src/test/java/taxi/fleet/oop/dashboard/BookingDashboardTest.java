package taxi.fleet.oop.dashboard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import taxi.fleet.oop.booking.BookingCenter;

class BookingDashboardTest {

    @Test
    void getStatistics() {
        BookingCenter bookingCenter = mock(BookingCenter.class);
        BookingDashboard bookingDashboard = new BookingDashboard(bookingCenter);
        bookingDashboard.getStatistics();
        verify(bookingCenter).getStatistics();
    }
}