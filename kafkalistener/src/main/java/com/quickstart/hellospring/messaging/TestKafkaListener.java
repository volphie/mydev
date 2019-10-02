package com.quickstart.hellospring.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaListener{
    private static final Logger log = LoggerFactory.getLogger(KafkaListener.class);

    private final String TOPIC = "test-topic";

    @KafkaListener(topics = TOPIC)
    public void subscribe(String message, Acknowledgment ack) {
        log.info(String.format("Message Received : %s", message));

        // Kafka Offset Manual Commit
        ack.acknowledge();
    }
}