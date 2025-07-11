package io.github.juanmorschrott.infrastructure.out.persistence.mapper;

import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.out.persistence.entity.SearchEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchEntityToSearchConverter implements Converter<SearchEntity, Search> {

    @Override
    public Search convert(SearchEntity searchEntity) {

        return new Search(searchEntity.getHotelId(),
                searchEntity.getCheckIn(),
                searchEntity.getCheckOut(),
                searchEntity.getAges());
    }
}
