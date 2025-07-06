package com.avoris.infrastructure;

import com.avoris.domain.Search;
import com.avoris.domain.SearchCount;
import com.avoris.infrastructure.in.rest.dto.SearchRequestDto;
import com.avoris.infrastructure.out.persistence.entity.SearchEntity;

import java.time.LocalDate;
import java.util.List;

public class Fixtures {

    public static final String SEARCH_ID = "xxxxx";

    private Fixtures() {}

    public static SearchRequestDto generateSearchRequestDto() {

        return new SearchRequestDto.Builder()
                .hotelId("1234abc")
                .checkIn(LocalDate.of(2023, 12, 29))
                .checkOut(LocalDate.of(2023, 12, 31))
                .ages(List.of(30, 29, 1, 3))
                .build();
    }

    public static Search generateSearch() {

        return new Search.Builder()
                .hotelId("1234abc")
                .checkIn(LocalDate.of(2023, 12, 29))
                .checkOut(LocalDate.of(2023, 12, 31))
                .ages(List.of(30, 29, 1, 3))
                .build();
    }

    public static SearchCount generateSearchCount() {

        return new SearchCount.Builder()
                .searchId(SEARCH_ID)
                .search(generateSearch())
                .count(1L)
                .build();
    }

    public static SearchEntity generateSearchEntity() {

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setHotelId("1234abc");
        searchEntity.setCheckIn(LocalDate.of(2023, 12, 29));
        searchEntity.setCheckOut(LocalDate.of(2023, 12, 31));
        searchEntity.setAges(List.of(30, 29, 1, 3));

        return searchEntity;
    }
}
