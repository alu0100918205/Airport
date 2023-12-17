/**
 * @file
 * @brief Contiene pruebas unitarias para la clase Flight.
 */

import es.ull.passengers.Passenger;
import es.ull.flights.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Pruebas unitarias para la clase Flight.
 */
class FlightTest {

    /**
     * @brief Verifica que se cree correctamente un vuelo con un número válido.
     */
    @Test
    void testValidFlightNumber() {
        Flight flight = new Flight("AB123", 100);
        assertEquals("AB123", flight.getFlightNumber());
    }

    /**
     * @brief Verifica que se lance una excepción al intentar crear un vuelo con un número inválido.
     */
    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("InvalidFlightNumber", 100));
    }

    /**
     * @brief Verifica que se añadan correctamente pasajeros a un vuelo y se actualice el número de pasajeros.
     */
    @Test
    void testAddPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("John", "Doe", "US");
        Passenger passenger2 = new Passenger("Jane", "Doe", "US");

        assertTrue(flight.addPassenger(passenger1));
        assertTrue(flight.addPassenger(passenger2));

        assertEquals(2, flight.getNumberOfPassengers());
    }

    /**
     * @brief Verifica que se lance una excepción al intentar añadir un pasajero a un vuelo lleno.
     */
    @Test
    void testAddPassengerExceedingSeats() {
        Flight flight = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("John", "Doe", "US");
        Passenger passenger2 = new Passenger("Jane", "Doe", "US");

        assertTrue(flight.addPassenger(passenger1));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
        assertEquals("Not enough seats for flight AB123", exception.getMessage());
    }

    /**
     * @brief Verifica que se elimine correctamente un pasajero de un vuelo y se actualice el número de pasajeros.
     */
    @Test
    void testRemovePassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("John", "Doe", "US");
        Passenger passenger2 = new Passenger("Jane", "Doe", "US
