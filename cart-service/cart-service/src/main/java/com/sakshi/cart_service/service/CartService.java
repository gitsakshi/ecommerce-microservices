package com.sakshi.cart_service.service;

import com.sakshi.cart_service.model.*;
import com.sakshi.cart_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sakshi.cart_service.client.ProductClient;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final ProductClient productClient;


    // ADD TO CART
    public Cart addToCart(Long userId, Long productId, int qty) {

        if (qty < 1) {
            throw new RuntimeException("Quantity must be >= 1");
        }

        // Find or create cart
        Cart cart = cartRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepo.save(newCart);
                });

        // Check if product already exists in cart
        Optional<CartItem> existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + qty);

        } else {

            // Fetch product from product-service
            ProductClient.ProductResponse product =
                    productClient.getProduct(productId);

            if (product == null) {
                throw new RuntimeException("Product not found!");
            }


            CartItem newItem = new CartItem();
            newItem.setProductId(productId);
            newItem.setQuantity(qty);
            newItem.setPriceAtTime(product.getPrice());
            newItem.setCart(cart);

            cart.getItems().add(newItem);
        }

        // Recalculate cart total
        recalcTotal(cart);

        return cartRepo.save(cart);
    }

    // UPDATE QUANTITY
    public Cart updateQuantity(Long userId, Long productId, int qty) {

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (qty == 0) {
            cart.getItems().remove(item);
            cartItemRepo.delete(item);
        } else {
            item.setQuantity(qty);
        }

        recalcTotal(cart);
        return cartRepo.save(cart);
    }

    // REMOVE ITEM
    public Cart removeItem(Long userId, Long productId) {

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cart.getItems().remove(item);
        cartItemRepo.delete(item);

        recalcTotal(cart);
        return cartRepo.save(cart);
    }

    // VIEW CART
    public Cart getCart(Long userId) {
        return cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // CLEAR ENTIRE CART
    public void clearCart(Long userId) {

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        //cartItemRepo.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setTotal(0);

        cartRepo.save(cart);
    }

    // TOTAL CALCULATION
    private void recalcTotal(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getPriceAtTime() * i.getQuantity())
                .sum();

        cart.setTotal(total);
    }
}