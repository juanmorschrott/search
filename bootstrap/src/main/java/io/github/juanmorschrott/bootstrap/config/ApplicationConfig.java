package io.github.juanmorschrott.bootstrap.config;

import io.github.juanmorschrott.application.port.in.PublishSearchUseCase;
import io.github.juanmorschrott.application.port.in.SearchCountQuery;
import io.github.juanmorschrott.application.port.in.SearchCreatedEventListener;
import io.github.juanmorschrott.application.port.in.SearchQuery;
import io.github.juanmorschrott.application.port.out.SearchEventPort;
import io.github.juanmorschrott.application.port.out.SearchPersistencePort;
import io.github.juanmorschrott.application.service.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    SearchService searchService(SearchEventPort searchEventPort, SearchPersistencePort searchPersistencePort) {
        return new SearchService(searchEventPort, searchPersistencePort);
    }

    @Bean
    PublishSearchUseCase publishSearchUseCase(SearchService searchService) {
        return searchService;
    }

    @Bean
    SearchCountQuery searchCountQuery(SearchService searchService) {
        return searchService;
    }

    @Bean
    SearchQuery searchQuery(SearchService searchService) {
        return searchService;
    }

    @Bean
    SearchCreatedEventListener searchCreatedEventListener(SearchService searchService) {
        return searchService;
    }
}
