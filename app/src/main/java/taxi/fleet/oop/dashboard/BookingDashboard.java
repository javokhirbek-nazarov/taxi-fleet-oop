package taxi.fleet.oop.dashboard;

import taxi.fleet.oop.booking.BookingCenter;

public class BookingDashboard extends Dashboard {

    private final BookingCenter bookingCenter;

    public BookingDashboard(BookingCenter bookingCenter) {
        this.bookingCenter = bookingCenter;
    }

    @Override
    public String getStatistics() {
        return bookingCenter.getStatistics().toString();
    }
}
