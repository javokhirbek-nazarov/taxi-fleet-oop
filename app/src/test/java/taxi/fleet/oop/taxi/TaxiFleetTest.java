package taxi.fleet.oop.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TaxiFleetTest {

    private TaxiFleet taxiFleet;

    @BeforeEach
    void setUp() {
        taxiFleet = new TaxiFleet();
    }

    @Test
    void addTaxi() {
        var mockTaxi = Mockito.mock(Taxi.class);
        taxiFleet.addTaxi(mockTaxi);

        List<Taxi> taxis = taxiFleet.getAllTaxis();

        assertEquals(1, taxis.size());
        assertTrue(taxis.contains(mockTaxi));
    }
}