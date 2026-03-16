package com.sakshi.order_service.service;

import com.sakshi.order_service.model.InventoryConfirmedEvent;
import com.sakshi.order_service.model.Order;
import com.sakshi.order_service.model.OrderStatus;
import com.sakshi.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "inventory-confirmed-topic", groupId = "order-group")
    public void consume(InventoryConfirmedEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow();

        order.setStatus(OrderStatus.INVENTORY_CONFIRMED);

        orderRepository.save(order);

        System.out.println("Order " + order.getId() + " status updated to INVENTORY_CONFIRMED");
    }
}