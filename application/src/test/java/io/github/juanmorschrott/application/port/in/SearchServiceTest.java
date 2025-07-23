package io.github.juanmorschrott.application.port.in;

import io.github.juanmorschrott.application.port.out.SearchEventPort;
import io.github.juanmorschrott.application.port.out.SearchPersistencePort;
import io.github.juanmorschrott.application.service.SearchService;
import io.github.juanmorschrott.domain.model.Search;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("SearchService unit tests")
class SearchServiceTest {

    private static final String HOTEL_ID = "hotel-123";
    private static final LocalDate CHECK_IN = LocalDate.of(2024, 10, 20);
    private static final LocalDate CHECK_OUT = LocalDate.of(2024, 10, 25);
    private static final List<Integer> AGES = List.of(30, 5);

    @InjectMocks
    private SearchService searchService;

    @Mock
    private SearchPersistencePort searchPersistencePort;

    @Mock
    private SearchEventPort searchEventPort;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Captor
    private ArgumentCaptor<Search> searchCaptor;

    @Test
    @DisplayName("Should publish an search event correctly")
    void should_publishAnSearchEvent() {
        Search searchToPublish = new Search(HOTEL_ID, CHECK_IN, CHECK_OUT, AGES);

        searchService.publish(searchToPublish);

        verify(searchEventPort).sendMessage(stringCaptor.capture(), searchCaptor.capture());

        String capturedId = stringCaptor.getValue();
        Search capturedSearch = searchCaptor.getValue();

        assertThat(capturedId).isNotNull().isNotBlank();
        assertThat(capturedSearch).isEqualTo(searchToPublish);
        assertThat(capturedSearch.hotelId()).isEqualTo(HOTEL_ID);
    }

    @Test
    @DisplayName("Should store a received search from an event")
    void should_storeReceivedSearch() {
        String searchId = UUID.randomUUID().toString();
        Search searchToSave = new Search(HOTEL_ID, CHECK_IN, CHECK_OUT, AGES);

        searchService.listen(searchId, searchToSave);

        verify(searchPersistencePort).save(stringCaptor.capture(), searchCaptor.capture());

        assertThat(stringCaptor.getValue()).isEqualTo(searchId);
        assertThat(searchCaptor.getValue()).isEqualTo(searchToSave);
    }

    @Test
    @DisplayName("Debe encontrar una búsqueda por ID")
    void should_findSearchById() {
        String searchId = UUID.randomUUID().toString();
        Search foundSearch = new Search(HOTEL_ID, CHECK_IN, CHECK_OUT, AGES);

        when(searchPersistencePort.findById(searchId)).thenReturn(foundSearch);

        Search result = searchService.getById(searchId);

        verify(searchPersistencePort).findById(searchId);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(foundSearch);
    }

    @Test
    @DisplayName("Debe contar búsquedas por criterios")
    void should_countSearchesByCriteria() {
        long expectedCount = 5L;
        when(searchPersistencePort.countSearchesByCriteria(HOTEL_ID, CHECK_IN, CHECK_OUT)).thenReturn(expectedCount);

        long count = searchService.search(HOTEL_ID, CHECK_IN, CHECK_OUT);

        verify(searchPersistencePort).countSearchesByCriteria(HOTEL_ID, CHECK_IN, CHECK_OUT);
        assertThat(count).isEqualTo(expectedCount);
    }
}