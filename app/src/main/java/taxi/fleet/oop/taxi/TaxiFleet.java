package taxi.fleet.oop.taxi;

import java.util.ArrayList;
import java.util.List;

public class TaxiFleet {

    private final List<Taxi> taxis;

    public TaxiFleet() {
        this.taxis = new ArrayList<>();
    }

    public void addTaxi(Taxi taxi) {
        taxis.add(taxi);
    }

    public List<Taxi> getAllTaxis() {
        return taxis;
    }

    public void listAllTaxis() {
        System.out.println("All taxis in the company fleet: \n");
        for (Taxi taxi : taxis) {
            System.out.println(taxi);
        }
        System.out.println();
    }
}
