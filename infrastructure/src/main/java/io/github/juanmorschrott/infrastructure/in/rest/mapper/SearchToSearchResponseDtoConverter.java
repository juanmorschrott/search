package io.github.juanmorschrott.infrastructure.in.rest.mapper;

import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchToSearchResponseDtoConverter implements Converter<Search, SearchResponseDto> {

    @Override
    public SearchResponseDto convert(Search search) {

        return new SearchResponseDto.Builder()
                .searchId(search.hotelId())
                .build();
    }
}
