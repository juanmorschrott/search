package io.github.juanmorschrott.infrastructure.in.rest;


import io.github.juanmorschrott.application.port.in.PublishSearchUseCase;
import io.github.juanmorschrott.application.port.in.SearchCountQuery;
import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.domain.model.SearchCount;
import io.github.juanmorschrott.infrastructure.Fixtures;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchCountResponseDto;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchRequestDto;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static io.github.juanmorschrott.infrastructure.Fixtures.SEARCH_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private PublishSearchUseCase publishSearchUseCase;

    @Mock
    private ConversionService conversionService;

    @Mock
    private SearchCountQuery searchCountQuery;

    @Test
    void testSearch() {
        SearchRequestDto searchRequestDto = Fixtures.generateSearchRequestDto();
        Search search = Fixtures.generateSearch();

        when(conversionService.convert(any(), eq(Search.class))).thenReturn(search);
        when(publishSearchUseCase.publish(any())).thenReturn(SEARCH_ID);

        SearchResponseDto response = searchController.search(searchRequestDto);

        verify(conversionService).convert(searchRequestDto, Search.class);
        verify(publishSearchUseCase).publish(search);
        assertEquals(SEARCH_ID, response.getSearchId());
    }

    @Test
    void testCount() {
        SearchCount searchCount = Fixtures.generateSearchCount();
        SearchCountResponseDto searchCountResponseDto = new SearchCountResponseDto();

        when(searchCountQuery.search(SEARCH_ID)).thenReturn(searchCount);
        when(conversionService.convert(searchCount, SearchCountResponseDto.class)).thenReturn(searchCountResponseDto);

        SearchCountResponseDto response = searchController.count(SEARCH_ID);

        verify(searchCountQuery).search(SEARCH_ID);
        verify(conversionService).convert(searchCount, SearchCountResponseDto.class);
        assertThat(response).isEqualTo(searchCountResponseDto);
    }
}