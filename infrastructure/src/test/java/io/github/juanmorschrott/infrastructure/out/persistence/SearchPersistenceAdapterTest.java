package io.github.juanmorschrott.infrastructure.out.persistence;

import io.github.juanmorschrott.domain.exception.SearchNotFoundException;
import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.Fixtures;
import io.github.juanmorschrott.infrastructure.out.persistence.entity.SearchEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchPersistenceAdapterTest {

    @InjectMocks
    private SearchPersistenceAdapter searchPersistenceAdapter;

    @Mock
    private ConversionService conversionService;

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private SearchCustomRepository searchCustomRepository;

    private String searchId;
    private Search search;
    private SearchEntity searchEntity;

    @BeforeEach
    void setUp() {
        searchId = "xxxxx";
        search = Fixtures.generateSearch();
        searchEntity = Fixtures.generateSearchEntity();
    }

    @Test
    void save() {
        when(searchRepository.save(any(SearchEntity.class))).thenReturn(searchEntity);
        when(conversionService.convert(any(SearchEntity.class), eq(Search.class))).thenReturn(search);

        Search result = this.searchPersistenceAdapter.save(searchId, search);

        verify(searchRepository).save(any(SearchEntity.class));
        assertEquals(search, result);
    }

    @Test
    void findById_whenNotFoundThrowsException() {
        String id = "nonexistentId";

        when(searchRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(SearchNotFoundException.class, () -> searchPersistenceAdapter.findById(id));
    }

    @Test
    void countSearchesByCriteria() {

        when(searchCustomRepository.countSearchesByCriteria(anyString(), any(), any()))
                .thenReturn(1L);

        long count = searchPersistenceAdapter.countSearchesByCriteria("1234abc",
                LocalDate.of(2023, 12, 29), LocalDate.of(2023, 12, 31));

        assertEquals(1L, count);
    }
}