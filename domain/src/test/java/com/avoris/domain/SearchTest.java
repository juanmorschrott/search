package com.avoris.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTest {

    @Test
    void testBuilder() {
        Search search = new Search.Builder()
                .hotelId("1234abc")
                .checkIn(LocalDate.of(2023, 12, 25))
                .checkOut(LocalDate.of(2023, 12, 30))
                .ages(List.of(1, 3, 5))
                .build();

        assertEquals(search.getHotelId(), "1234abc");
        assertEquals(search.getCheckIn(), LocalDate.of(2023, 12, 25));
        assertEquals(search.getCheckOut(), LocalDate.of(2023, 12, 30));
        assertEquals(search.getAges(), List.of(1, 3, 5));
    }
}