package com.sakshi.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    @Data
    class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private double price;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

            public String getName() { return name; }
            public void setName(String name) { this.name = name; }

            public double getPrice() { return price; }
            public void setPrice(double price) { this.price = price; }



    }
}