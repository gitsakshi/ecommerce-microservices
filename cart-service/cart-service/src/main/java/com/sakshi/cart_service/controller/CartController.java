package com.sakshi.cart_service.controller;

import com.sakshi.cart_service.model.Cart;
import com.sakshi.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // ADD TO CART
    @PostMapping("/add")
    public Cart add(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int qty) {

        return cartService.addToCart(userId, productId, qty);
    }

    // UPDATE QUANTITY
    @PutMapping("/update")
    public Cart update(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int qty) {

        return cartService.updateQuantity(userId, productId, qty);
    }

    // REMOVE SINGLE ITEM
    @DeleteMapping("/remove")
    public Cart removeItem(
            @RequestParam Long userId,
            @RequestParam Long productId) {

        return cartService.removeItem(userId, productId);
    }

    // CLEAR ENTIRE CART
    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }

    // VIEW CART
    @GetMapping("/{userId}")
    public Cart view(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }
}