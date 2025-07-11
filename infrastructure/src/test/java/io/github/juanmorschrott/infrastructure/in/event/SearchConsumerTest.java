package io.github.juanmorschrott.infrastructure.in.event;


import io.github.juanmorschrott.application.port.in.SearchCreatedEventListener;
import io.github.juanmorschrott.domain.model.Search;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SearchConsumerTest {

    @Mock
    private SearchCreatedEventListener searchCreatedEventListener;

    @InjectMocks
    private SearchConsumer searchConsumer;

    @Test
    void testListen() {
        String searchId = UUID.randomUUID().toString();
        Search search = new Search(null, null, null, null);
        ConsumerRecord<String, Search> consumerRecord = new ConsumerRecord<>("hotel_availability_searches",
                0, 0, searchId, search);

        searchConsumer.listen(consumerRecord);

        verify(searchCreatedEventListener).listen(anyString(), any());
    }
}