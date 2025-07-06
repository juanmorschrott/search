package com.avoris.infrastructure.out.persistence;

import com.avoris.infrastructure.out.persistence.entity.SearchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class SearchCustomRepositoryImpl implements SearchCustomRepository {

    private static final Logger log = LoggerFactory.getLogger(SearchCustomRepositoryImpl.class);

    private static final String HOTEL_ID = "hotelId";
    private static final String CHECK_IN = "checkIn";
    private static final String CHECK_OUT = "checkOut";

    private final MongoTemplate mongoTemplate;

    public SearchCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long countSearchesByCriteria(String hotelId, LocalDate checkIn, LocalDate checkOut) {
        log.info("countSearchesByCriteria: {}, {}, {}", hotelId, checkIn, checkOut);

        Query query = new Query();

        if (hotelId != null && !hotelId.isEmpty()) {
            query.addCriteria(Criteria.where(HOTEL_ID).is(hotelId));
        }
        if (checkIn != null) {
            query.addCriteria(Criteria.where(CHECK_IN).is(checkIn));
        }
        if (checkOut != null) {
            query.addCriteria(Criteria.where(CHECK_OUT).is(checkOut));
        }

        return mongoTemplate.count(query, SearchEntity.class);
    }
}
