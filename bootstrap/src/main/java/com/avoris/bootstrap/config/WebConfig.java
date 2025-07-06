package com.avoris.bootstrap.config;

import com.avoris.infrastructure.in.rest.mapper.SearchCountToSearchCountResponseDtoConverter;
import com.avoris.infrastructure.in.rest.mapper.SearchRequestDtoToSearchConverter;
import com.avoris.infrastructure.in.rest.mapper.SearchToSearchResponseDtoConverter;
import com.avoris.infrastructure.out.persistence.mapper.SearchEntityToSearchConverter;
import com.avoris.infrastructure.out.persistence.mapper.SearchToSearchEntityConverter;
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
