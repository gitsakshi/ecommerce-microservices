package com.sakshi.cart_service.model;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int quantity;
    private double priceAtTime;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    public CartItem(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public Long getProductId(){ return productId; }
    public void setProductId(Long productId){ this.productId = productId; }

    public int getQuantity(){ return quantity; }
    public void setQuantity(int quantity){ this.quantity = quantity; }

    public double getPriceAtTime(){ return priceAtTime; }
    public void setPriceAtTime(double priceAtTime){ this.priceAtTime = priceAtTime; }

    public Cart getCart(){ return cart; }
    public void setCart(Cart cart){ this.cart = cart; }
}

