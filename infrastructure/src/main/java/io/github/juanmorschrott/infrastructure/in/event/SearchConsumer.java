package io.github.juanmorschrott.infrastructure.in.event;

import io.github.juanmorschrott.application.port.in.SearchCreatedEventListener;
import io.github.juanmorschrott.domain.model.Search;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SearchConsumer {

    private static final Logger log = LoggerFactory.getLogger(SearchConsumer.class);

    private final SearchCreatedEventListener searchCreatedEventListener;

    public SearchConsumer(SearchCreatedEventListener searchCreatedEventListener) {
        this.searchCreatedEventListener = searchCreatedEventListener;
    }

    @KafkaListener(topics = "hotel_availability_searches", groupId = "search-group")
    public void listen(ConsumerRecord<String, Search> consumerRecord) {
        log.info("Received consumerRecord: {}", consumerRecord);
        String searchId = consumerRecord.key();
        Search search = consumerRecord.value();

        this.searchCreatedEventListener.listen(searchId, search);
    }
}
