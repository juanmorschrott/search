package io.github.juanmorschrott.domain.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTest {

    @Test
    void testBuilder() {
        Search search = new Search("1234abc",
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 30),
                List.of(1, 3, 5));

        assertEquals("1234abc", search.hotelId());
        assertEquals(search.checkIn(), LocalDate.of(2023, 12, 25));
        assertEquals(search.checkOut(), LocalDate.of(2023, 12, 30));
        assertEquals(search.ages(), List.of(1, 3, 5));
    }
}