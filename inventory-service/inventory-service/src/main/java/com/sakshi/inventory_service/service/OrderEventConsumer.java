// Here we will reduce stock
package com.sakshi.inventory_service.service;

import com.sakshi.inventory_service.model.InventoryConfirmedEvent;
import com.sakshi.inventory_service.model.OrderPlacedEvent;
import com.sakshi.inventory_service.model.ProcessedEvent;
import com.sakshi.inventory_service.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final InventoryService inventoryService;
    private final KafkaProducer kafkaProducer;
    private final ProcessedEventRepository processedEventRepository;


    @KafkaListener(topics = "order-topic", groupId = "inventory-group")
    public void consume(OrderPlacedEvent event) {

        System.out.println(
                "Processing order " + event.getOrderId() +
                        " for product " + event.getProductId()
        );

        inventoryService.reduceStock(
                event.getProductId(),
                event.getQuantity()
        );

        processedEventRepository.save(new ProcessedEvent(event.getOrderId()));

        kafkaProducer.sendInventoryConfirmedEvent(
                new InventoryConfirmedEvent(event.getOrderId())
        );
    }
}