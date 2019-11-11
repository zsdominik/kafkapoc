package com.zsirosd.kafakapoc.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {

    @KafkaListener(topics = "topic1", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    public void consumeG1(String message) {
        System.out.println("Received message in group1 from topic 1: " + message);
    }

    @KafkaListener(topics = "topic2", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    public void consumeG2(String message) {
        System.out.println("Received message in group1 from topic 2: " + message);
    }
}
