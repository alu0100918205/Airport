import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testValidPassengerCreation() {
        Passenger passenger = new Passenger("John123", "John Doe", "US");

        assertEquals("John123", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlight() {
        Passenger passenger = new Passenger("Jane456", "Jane Doe", "US");
        Flight flight = new Flight("AB456", 3);

        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
        assertTrue(flight.getNumberOfPassengers() <= flight.getSeats());
    }

    @Test
    void testLeaveFlight() {
        Passenger passenger = new Passenger("Alice789", "Alice Smith", "US");
        Flight flight = new Flight("CD789", 2);

        passenger.joinFlight(flight);
        passenger.joinFlight(null);

        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }


    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("InvalidID", "Invalid Name", "XX"));
    }
}
