package io.github.juanmorschrott.application.port.in;

import java.time.LocalDate;

/**
 * Example of input for simple query which does not modify the state of the system.
 */
public interface SearchCountQuery {

    long search(String hotelId, LocalDate checkIn, LocalDate checkOut);
}
