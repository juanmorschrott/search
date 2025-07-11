package io.github.juanmorschrott.application.port.out;

import io.github.juanmorschrott.domain.model.Search;

import java.time.LocalDate;

public interface SearchPersistencePort {

    Search save(String searchId, Search search);

    Search findById(String id);

    long countSearchesByCriteria(String hotelId, LocalDate checkIn, LocalDate checkOut);
}
