package io.github.juanmorschrott.infrastructure.in.rest.mapper;

import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchRequestDtoToSearchConverter implements Converter<SearchRequestDto, Search> {

    @Override
    public Search convert(SearchRequestDto searchRequestDto) {

        return new Search(searchRequestDto.getHotelId(),
                searchRequestDto.getCheckIn(),
                searchRequestDto.getCheckOut(),
                searchRequestDto.getAges());
    }
}
