package com.avoris.infrastructure.out.persistence.mapper;

import com.avoris.domain.Search;
import com.avoris.infrastructure.out.persistence.entity.SearchEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchToSearchEntityConverter implements Converter<Search, SearchEntity> {

    @Override
    public SearchEntity convert(Search search) {

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setHotelId(search.getHotelId());
        searchEntity.setCheckIn(search.getCheckIn());
        searchEntity.setCheckOut(search.getCheckOut());
        searchEntity.setAges(search.getAges());

        return searchEntity;
    }
}
