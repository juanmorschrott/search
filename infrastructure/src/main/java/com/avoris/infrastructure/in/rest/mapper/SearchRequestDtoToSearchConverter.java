package com.avoris.infrastructure.in.rest.mapper;

import com.avoris.domain.Search;
import com.avoris.infrastructure.in.rest.dto.SearchRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchRequestDtoToSearchConverter implements Converter<SearchRequestDto, Search> {

    @Override
    public Search convert(SearchRequestDto searchRequestDto) {

        return new Search.Builder()
                .hotelId(searchRequestDto.getHotelId())
                .checkIn(searchRequestDto.getCheckIn())
                .checkOut(searchRequestDto.getCheckOut())
                .ages(searchRequestDto.getAges())
                .build();
    }
}
