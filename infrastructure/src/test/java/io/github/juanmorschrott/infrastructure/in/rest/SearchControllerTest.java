package io.github.juanmorschrott.infrastructure.in.rest;


import io.github.juanmorschrott.application.port.in.PublishSearchUseCase;
import io.github.juanmorschrott.application.port.in.SearchCountQuery;
import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.Fixtures;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchRequestDto;
import io.github.juanmorschrott.infrastructure.in.rest.dto.SearchResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.time.LocalDate;

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
        String hotelId = "hotel-123";
        LocalDate checkIn = LocalDate.of(2024, 6, 1);
        LocalDate checkOut = LocalDate.of(2024, 6, 5);

        when(searchCountQuery.search(hotelId, checkIn, checkOut)).thenReturn(2L);

        long response = searchController.count(hotelId, checkIn, checkOut);

        verify(searchCountQuery).search(hotelId, checkIn, checkOut);
        assertThat(response).isEqualTo(2L);
    }
}