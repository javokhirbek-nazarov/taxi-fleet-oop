package taxi.fleet.oop.taxi;

import java.util.Random;
import taxi.fleet.oop.booking.Booking;
import taxi.fleet.oop.booking.BookingStatus;
import taxi.fleet.oop.communication.MessageCommunicationAgent;
import taxi.fleet.oop.communication.TaxiAgent;
import taxi.fleet.oop.observer.Observable;
import taxi.fleet.oop.observer.Observer;

public class Taxi implements Observer<Booking> {

    private final String id;

    private String location;

    private TaxiStatus status;

    private final Observable<Booking> observer;

    private final MessageCommunicationAgent taxiAgent;

    public Taxi(String id, String location, Observable<Booking> bookingCenter) {
        this.id = id;
        this.location = location;
        this.observer = bookingCenter;
        this.taxiAgent = new TaxiAgent(this);
        setStatus(TaxiStatus.AVAILABLE);
    }

    public void update(Booking booking) throws InterruptedException {

        //To simulate waiting time before response
        Random random = new Random();
        Thread.sleep(random.nextInt(100));

        if (TaxiStatus.BOOKED.equals(status)) {
            throw new IllegalStateException("Can not be updated with booking when already booked");
        } else if (BookingStatus.NEW.equals(booking.getStatus())) {
            var took = booking.take(this);
            if (took) {
                setStatus(TaxiStatus.BOOKED);
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TaxiStatus getStatus() {
        return status;
    }

    public void setStatus(TaxiStatus status) {
        this.status = status;
        if (status == TaxiStatus.AVAILABLE) {
            observer.subscribe(this);
        } else {
            observer.unSubscribe(this);
        }
    }

    public MessageCommunicationAgent getMessageCommunicationAgent() {
        return taxiAgent;
    }

    @Override
    public String toString() {
        return "Taxi{" +
            "id='" + id + '\'' +
            ", location='" + location + '\'' +
            ", status=" + status +
            '}';
    }
}
