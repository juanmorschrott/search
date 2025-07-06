package com.avoris.bootstrap.config;

import com.avoris.application.port.in.PublishSearchUseCase;
import com.avoris.application.port.in.SearchCountQuery;
import com.avoris.application.port.in.SearchCreatedEventListener;
import com.avoris.application.port.out.SearchEventPort;
import com.avoris.application.port.out.SearchPersistencePort;
import com.avoris.application.service.SearchService;
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
    SearchCreatedEventListener searchCreatedEventListener(SearchService searchService) {
        return searchService;
    }
}
