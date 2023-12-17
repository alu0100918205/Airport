/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * @brief Represents a passenger with an identifier, name, and country code.
 */
public class Passenger {

    private String identifier; ///< Unique identifier for the passenger.
    private String name; ///< Name of the passenger.
    private String countryCode; ///< Country code of the passenger.
    private Flight flight; ///< Flight associated with the passenger.

    /**
     * @brief Constructs a Passenger object with the specified identifier, name, and country code.
     * @param identifier The unique identifier for the passenger.
     * @param name The name of the passenger.
     * @param countryCode The country code of the passenger.
     * @throws RuntimeException if the provided country code is invalid.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Gets the unique identifier for the passenger.
     * @return The identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Gets the name of the passenger.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Gets the country code of the passenger.
     * @return The country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Gets the flight associated with the passenger.
     * @return The associated flight.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Joins a flight, updating the passenger's association with the flight.
     * @param flight The flight to join.
     * @throws RuntimeException if the passenger cannot be added to or removed from a flight.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Sets the flight associated with the passenger.
     * @param flight The flight to set.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Provides a string representation of the passenger.
     * @return A string containing the name, identifier, and country code of the passenger.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
