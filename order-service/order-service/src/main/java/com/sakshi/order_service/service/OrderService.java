package com.sakshi.order_service.service;

import com.sakshi.order_service.model.OrderPlacedEvent;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sakshi.order_service.repository.OrderRepository;
import com.sakshi.order_service.repository.OrderItemRepository;
import com.sakshi.order_service.client.CartClient;
import com.sakshi.order_service.dto.CartItemDto;
import com.sakshi.order_service.dto.CartResponse;
import com.sakshi.order_service.model.Order;
import com.sakshi.order_service.model.OrderItem;
import com.sakshi.order_service.model.OrderStatus;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartClient cartClient;
    private final KafkaProducer kafkaProducer;


    @Transactional
    @CircuitBreaker(name = "inventoryBreaker", fallbackMethod = "inventoryFallback")
    public Order checkout(Long userId) {

        System.out.println("Checkout started");
        //1. fetch cart
        CartResponse cart = cartClient.getCart(userId);

        if (cart.getItems() == null || cart.getItems().isEmpty()){
            throw new RuntimeException("The cart is Empty!");
        }


        //System.out.println("SKU from cart: " + item.getSkuCode());



        //3. calculate total
        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getPriceAtTime()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //4. create order
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);


        // 5. Save OrderItems
        for (CartItemDto item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPriceAtTime());

            orderItemRepository.save(orderItem);

            System.out.println("Publishing order event");

            // Publish event
            OrderPlacedEvent event = new OrderPlacedEvent(
                    savedOrder.getId(),
                    item.getProductId(),
                    item.getQuantity()
            );

            kafkaProducer.sendOrderEvent(event);

            System.out.println("Kafka event received: " + event);
        }

        // 6. Clear cart
        cartClient.clearCart(userId);

        return savedOrder;
    }

       //fallback
       public Order inventoryFallback(Long userId, Throwable throwable) {
           throwable.printStackTrace();   // VERY IMPORTANT
           throw new RuntimeException(throwable);
       }


}
