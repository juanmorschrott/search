package com.avoris.infrastructure.out.persistence;

import java.time.LocalDate;

public interface SearchCustomRepository {

    long countSearchesByCriteria(String hotelId, LocalDate checkIn, LocalDate checkOut);
}
