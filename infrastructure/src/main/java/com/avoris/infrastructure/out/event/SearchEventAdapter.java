package com.avoris.infrastructure.out.event;

import com.avoris.application.port.out.SearchEventPort;
import com.avoris.domain.model.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SearchEventAdapter implements SearchEventPort {

    private static final Logger log = LoggerFactory.getLogger(SearchEventAdapter.class);

    @Value("${kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, Search> kafkaTemplate;

    public SearchEventAdapter(KafkaTemplate<String, Search> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String searchId, Search search) {
        log.info("Sending Message: {}", search);
        this.kafkaTemplate.send(topic, searchId, search);
    }
}
