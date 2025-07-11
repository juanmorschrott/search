package io.github.juanmorschrott.application.service;

import io.github.juanmorschrott.application.port.in.PublishSearchUseCase;
import io.github.juanmorschrott.application.port.in.SearchCountQuery;
import io.github.juanmorschrott.application.port.in.SearchCreatedEventListener;
import io.github.juanmorschrott.application.port.out.SearchEventPort;
import io.github.juanmorschrott.application.port.out.SearchPersistencePort;
import io.github.juanmorschrott.domain.exception.SearchServiceException;
import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.domain.model.SearchCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class SearchService implements PublishSearchUseCase, SearchCreatedEventListener, SearchCountQuery {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    private final SearchEventPort searchEventPort;
    private final SearchPersistencePort searchPersistencePort;

    public SearchService(SearchEventPort searchEventPort, SearchPersistencePort searchPersistencePort) {
        this.searchEventPort = searchEventPort;
        this.searchPersistencePort = searchPersistencePort;
    }

    @Override
    public String publish(Search search) {
        try {
            String searchId = UUID.randomUUID().toString();
            this.searchEventPort.sendMessage(searchId, search);

            return searchId;
        } catch (Exception e) {
            log.error("Failed to publish search: {}", e.getMessage());
            throw new SearchServiceException();
        }
    }

    @Override
    public void listen(String searchId, Search search) {
        try {
            this.searchPersistencePort.save(searchId, search);
        } catch (Exception e) {
            log.error("Failed to listen search: {}", e.getMessage());
            throw new SearchServiceException();
        }
    }

    @Override
    public SearchCount search(String searchId) {
        try {
            Search search = this.searchPersistencePort.findById(searchId);
            Long count = this.searchPersistencePort.countSearchesByCriteria(search.hotelId(), search.checkIn(), search.checkOut());

            return new SearchCount(searchId, search, count);
        } catch (Exception e) {
            log.error("Failed to search: {}", e.getMessage());
            throw new SearchServiceException();
        }
    }
}
