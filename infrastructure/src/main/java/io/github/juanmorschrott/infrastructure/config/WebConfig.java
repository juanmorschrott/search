package io.github.juanmorschrott.infrastructure.config;

import io.github.juanmorschrott.infrastructure.in.rest.mapper.SearchRequestDtoToSearchConverter;
import io.github.juanmorschrott.infrastructure.in.rest.mapper.SearchToSearchResponseDtoConverter;
import io.github.juanmorschrott.infrastructure.out.persistence.mapper.SearchEntityToSearchConverter;
import io.github.juanmorschrott.infrastructure.out.persistence.mapper.SearchToSearchEntityConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Global date format converter
        registry.addConverter(String.class, LocalDate.class, source -> {
            if (source.isEmpty()) return null;
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        });

        // Custom converters for search-related mappings
        registry.addConverter(new SearchRequestDtoToSearchConverter());
        registry.addConverter(new SearchToSearchEntityConverter());
        registry.addConverter(new SearchEntityToSearchConverter());
        registry.addConverter(new SearchToSearchResponseDtoConverter());
    }
}
