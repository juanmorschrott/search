package io.github.juanmorschrott.infrastructure.in.rest.mapper;

import io.github.juanmorschrott.domain.model.SearchCount;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchCountResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchCountToSearchCountResponseDtoConverter implements Converter<SearchCount, SearchCountResponseDto> {

    @Override
    public SearchCountResponseDto convert(SearchCount searchCount) {

        SearchCountResponseDto.Search search = new SearchCountResponseDto.Search.Builder()
                .hotelId(searchCount.search().hotelId())
                .checkIn(searchCount.search().checkIn())
                .checkOut(searchCount.search().checkOut())
                .ages(searchCount.search().ages())
                .build();

        return new SearchCountResponseDto.Builder()
                .searchId(searchCount.searchId())
                .search(search)
                .count(searchCount.count())
                .build();
    }
}
