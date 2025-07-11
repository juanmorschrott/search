package com.avoris.infrastructure.out.persistence.mapper;

import com.avoris.domain.model.Search;
import com.avoris.infrastructure.out.persistence.entity.SearchEntity;
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
