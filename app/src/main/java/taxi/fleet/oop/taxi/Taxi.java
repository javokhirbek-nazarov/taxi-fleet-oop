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

    private final Observable<Booking> observable;

    private final MessageCommunicationAgent taxiAgent;

    private Booking currentBooking;

    public Taxi(String id, String location, Observable<Booking> bookingCenter) {
        this.id = id;
        this.location = location;
        this.observable = bookingCenter;
        this.taxiAgent = new TaxiAgent(this);
        setStatus(TaxiStatus.AVAILABLE);
    }

    public void update(Booking booking) throws InterruptedException {

        //To simulate waiting time before response
        Random random = new Random();
        Thread.sleep(random.nextInt(1000));

        if (TaxiStatus.BOOKED.equals(status)) {
            throw new IllegalStateException("Can not be updated with booking when already booked");
        }

        var took = booking.take(this);
        if (took) {
            currentBooking = booking;
            setStatus(TaxiStatus.BOOKED);
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
            observable.subscribe(this);
        } else {
            observable.unSubscribe(this);
        }
    }

    public void setStatus(TaxiStatus status, boolean filterDown){
        if (currentBooking != null && filterDown) {
            currentBooking.complete();
            currentBooking = null;
        }else{
            setStatus(status);
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
