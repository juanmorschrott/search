package io.github.juanmorschrott.infrastructure.config;

import io.github.juanmorschrott.infrastructure.in.rest.mapper.SearchCountToSearchCountResponseDtoConverter;
import io.github.juanmorschrott.infrastructure.in.rest.mapper.SearchRequestDtoToSearchConverter;
import io.github.juanmorschrott.infrastructure.in.rest.mapper.SearchToSearchResponseDtoConverter;
import io.github.juanmorschrott.infrastructure.out.persistence.mapper.SearchEntityToSearchConverter;
import io.github.juanmorschrott.infrastructure.out.persistence.mapper.SearchToSearchEntityConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SearchRequestDtoToSearchConverter());
        registry.addConverter(new SearchToSearchEntityConverter());
        registry.addConverter(new SearchEntityToSearchConverter());
        registry.addConverter(new SearchToSearchResponseDtoConverter());
        registry.addConverter(new SearchCountToSearchCountResponseDtoConverter());
    }
}
