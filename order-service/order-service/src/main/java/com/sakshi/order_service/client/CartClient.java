package com.sakshi.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakshi.order_service.dto.CartResponse;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("api/cart/{userId}")
    CartResponse getCart(@PathVariable Long userId);

    @DeleteMapping("api/cart/clear/{userId}")
    void clearCart(@PathVariable Long userId);
}
