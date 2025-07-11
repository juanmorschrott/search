package com.avoris.application.port.in;

import com.avoris.application.port.out.SearchEventPort;
import com.avoris.application.port.out.SearchPersistencePort;
import com.avoris.application.service.SearchService;
import com.avoris.domain.model.Search;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private SearchPersistencePort searchPersistencePort;

    @Mock
    private SearchEventPort searchEventPort;

    @Test
    void publishTest() {
        Search search = new Search.Builder()
                .build();

        searchService.publish(search);

        verify(searchEventPort).sendMessage(anyString(), any());
    }

    @Test
    void listenTest() {

        searchService.listen("searchId", new Search.Builder().build());

        verify(searchPersistencePort).save(anyString(), any());
    }

    @Test
    void searchTest() {
        Search search = new Search.Builder()
                .hotelId("fakeHotelId")
                .checkIn(LocalDate.now())
                .checkIn(LocalDate.now())
                .ages(List.of(20, 24))
                .build();

        when(searchPersistencePort.findById(anyString())).thenReturn(search);

        searchService.search("fakeSearchId");

        verify(searchPersistencePort).findById(anyString());
        verify(searchPersistencePort).countSearchesByCriteria(anyString(), any(), any());
    }
}