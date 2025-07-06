package com.avoris.infrastructure.in.rest.mapper;

import com.avoris.domain.SearchCount;
import com.avoris.infrastructure.in.rest.dto.SearchCountResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchCountToSearchCountResponseDtoConverter implements Converter<SearchCount, SearchCountResponseDto> {

    @Override
    public SearchCountResponseDto convert(SearchCount searchCount) {

        SearchCountResponseDto.Search search = new SearchCountResponseDto.Search.Builder()
                .hotelId(searchCount.getSearch().getHotelId())
                .checkIn(searchCount.getSearch().getCheckIn())
                .checkOut(searchCount.getSearch().getCheckOut())
                .ages(searchCount.getSearch().getAges())
                .build();

        return new SearchCountResponseDto.Builder()
                .searchId(searchCount.getSearchId())
                .search(search)
                .count(searchCount.getCount())
                .build();
    }
}
