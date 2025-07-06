package com.avoris.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchCountTest {

    @Test
    void testBuilder() {
        String searchId = "search456";
        Search search = new Search.Builder()
                .hotelId("hotelX")
                .checkIn(LocalDate.of(2024, 05, 01))
                .checkOut(LocalDate.of(2024, 05, 07))
                .build();
        Long count = 25L;

        SearchCount searchCount = new SearchCount.Builder()
                .searchId(searchId)
                .search(search)
                .count(count)
                .build();

        assertEquals(searchCount.getSearchId(), searchId);
        assertEquals(searchCount.getSearch(), search);
        assertEquals(searchCount.getCount(), count);
    }
}