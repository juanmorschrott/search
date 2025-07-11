package com.avoris.domain.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchCountTest {

    @Test
    void testBuilder() {
        String searchId = "search456";
        Search search = new Search("hotelX",
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 7),
                List.of(1));
        Long count = 25L;

        SearchCount expectedSearchCount = new SearchCount(searchId, search, count);

        assertEquals(searchId, expectedSearchCount.searchId());
        assertEquals(expectedSearchCount.search(), search);
        assertEquals(count, expectedSearchCount.count());
    }
}