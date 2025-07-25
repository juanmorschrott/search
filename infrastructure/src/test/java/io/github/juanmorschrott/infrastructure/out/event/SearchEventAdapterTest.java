package io.github.juanmorschrott.infrastructure.out.event;

import io.github.juanmorschrott.domain.model.Search;
import io.github.juanmorschrott.infrastructure.Fixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SearchEventAdapterTest {

    @InjectMocks
    private SearchEventAdapter searchEventAdapter;

    @Mock
    private KafkaTemplate<String, Search> kafkaTemplate;

    @Test
    void sendMessageTest() {
        String searchId = UUID.randomUUID().toString();
        Search search = Fixtures.generateSearch();

        this.searchEventAdapter.sendMessage(searchId, search);

        verify(kafkaTemplate).send(any(), anyString(), any());
    }
}