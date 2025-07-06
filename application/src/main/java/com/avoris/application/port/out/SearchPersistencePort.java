package com.avoris.application.port.out;

import com.avoris.domain.Search;

import java.time.LocalDate;

public interface SearchPersistencePort {

    Search save(String searchId, Search search);

    Search findById(String id);

    long countSearchesByCriteria(String hotelId, LocalDate checkIn, LocalDate checkOut);
}
