package com.sakshi.order_service.service;

import com.sakshi.order_service.model.OrderPlacedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderPlacedEvent event) {
        System.out.println("Sending event to Kafka: " + event.getProductId());
        kafkaTemplate.send("order-topic", event);
    }
}
