package taxi.fleet.oop.taxi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaxiFleet {

    private final Map<String, Taxi> taxiMap;

    public TaxiFleet() {
        this.taxiMap = new HashMap<>();
    }

    public void addTaxi(Taxi taxi) {
        taxiMap.put(taxi.getId(), taxi);
    }

    public Optional<Taxi> getTaxi(String id) {
        return Optional.ofNullable(taxiMap.getOrDefault(id, null));
    }

    public List<Taxi> getAllTaxis() {
        return taxiMap.values().stream()
            .sorted(Comparator.comparing(Taxi::getId))
            .toList();
    }

    public void listAllTaxis() {
        System.out.println("All taxis in the company fleet: \n");
        for (Taxi taxi : getAllTaxis()) {
            System.out.println(taxi);
        }
        System.out.println();
    }
}
