package com.sakshi.inventory_service.service;

import com.sakshi.inventory_service.model.InventoryConfirmedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, InventoryConfirmedEvent> kafkaTemplate;

    public void sendInventoryConfirmedEvent(InventoryConfirmedEvent event){
        System.out.println("Inventory confirmed for order "+ event.getOrderId());
        kafkaTemplate.send("inventory-confirmed-topic", event);
    }
}
