package com.avoris.domain;


import com.avoris.domain.model.Search;
import com.avoris.domain.model.SearchCount;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchCountTest {

    @Test
    void testBuilder() {
        String searchId = "search456";
        Search search = new Search.Builder()
                .hotelId("hotelX")
                .checkIn(LocalDate.of(2024, 5, 1))
                .checkOut(LocalDate.of(2024, 5, 7))
                .build();
        Long count = 25L;

        SearchCount searchCount = new SearchCount.Builder()
                .searchId(searchId)
                .search(search)
                .count(count)
                .build();

        assertEquals(searchId, searchCount.getSearchId());
        assertEquals(searchCount.getSearch(), search);
        assertEquals(count, searchCount.getCount());
    }
}