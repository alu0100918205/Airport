import es.ull.passengers.Passenger;
import es.ull.flights.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testValidFlightNumber() {
        Flight flight = new Flight("AB123", 100);
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("InvalidFlightNumber", 100));
    }

    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("John", "Doe", "US");
        Passenger passenger2 = new Passenger("Jane", "Doe", "US");

        assertTrue(flight.addPassenger(passenger1));
        assertTrue(flight.addPassenger(passenger2));

        assertEquals(2, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassengerExceedingSeats() {
        Flight flight = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("John", "Doe", "US");
        Passenger passenger2 = new Passenger("Jane", "Doe", "US");

        assertTrue(flight.addPassenger(passenger1));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
        assertEquals("Not enough seats for flight AB123", exception.getMessage());
    }

    @Test
    void testRemovePassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("John", "Doe", "US");
        Passenger passenger2 = new Passenger("Jane", "Doe", "US");

        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);

        assertTrue(flight.removePassenger(passenger1));

        assertEquals(1, flight.getNumberOfPassengers());
    }
}
