package com.avoris.infrastructure.out.persistence;

import com.avoris.infrastructure.out.persistence.entity.SearchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends MongoRepository<SearchEntity, String> {


}
