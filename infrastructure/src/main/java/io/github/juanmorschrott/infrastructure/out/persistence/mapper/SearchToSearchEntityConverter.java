package io.github.juanmorschrott.infrastructure.out.persistence.mapper;

import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.out.persistence.entity.SearchEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SearchToSearchEntityConverter implements Converter<Search, SearchEntity> {

    @Override
    public SearchEntity convert(Search search) {

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setHotelId(search.hotelId());
        searchEntity.setCheckIn(search.checkIn());
        searchEntity.setCheckOut(search.checkOut());
        searchEntity.setAges(search.ages());

        return searchEntity;
    }
}
