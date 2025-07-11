package com.avoris.infrastructure.out.persistence;

import com.avoris.application.port.out.SearchPersistencePort;
import com.avoris.domain.model.Search;
import com.avoris.domain.exception.SearchNotFoundException;
import com.avoris.infrastructure.out.persistence.entity.SearchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class SearchPersistenceAdapter implements SearchPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(SearchPersistenceAdapter.class);

    private final ConversionService conversionService;
    private final SearchRepository searchRepository;
    private final SearchCustomRepository searchCustomRepository;

    public SearchPersistenceAdapter(ConversionService conversionService,
                                    SearchRepository searchRepository,
                                    SearchCustomRepository searchCustomRepository) {
        this.conversionService = conversionService;
        this.searchRepository = searchRepository;
        this.searchCustomRepository = searchCustomRepository;
    }

    @Override
    public Search save(String searchId, Search search) {
        log.info("Storing search: {}", search);

        return Optional.ofNullable(search)
                .map(s -> {
                    SearchEntity searchEntity = new SearchEntity();
                    searchEntity.setSearchId(searchId);
                    searchEntity.setHotelId(s.getHotelId());
                    searchEntity.setCheckIn(s.getCheckIn());
                    searchEntity.setCheckOut(s.getCheckOut());
                    searchEntity.setAges(s.getAges());

                    return searchEntity;
                })
                .map(this.searchRepository::save)
                .map(savedEntity -> this.conversionService.convert(savedEntity, Search.class))
                .orElse(null);
    }


    @Override
    public Search findById(String id) {
        log.info("Searching search by id: {}", id);

        SearchEntity searchEntity = this.searchRepository.findById(id)
                .orElseThrow(() -> new SearchNotFoundException("Search not found with id: " + id));

        return this.conversionService.convert(searchEntity, Search.class);
    }

    @Override
    public long countSearchesByCriteria(String hotelId, LocalDate checkIn, LocalDate checkOut) {
        log.info("Counting searches by hotelId: {}", hotelId);

        return this.searchCustomRepository.countSearchesByCriteria(hotelId, checkIn, checkOut);
    }
}
