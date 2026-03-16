package com.sakshi.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.sakshi.order_service.dto.InventoryRequest;

@FeignClient(name="inventory-service")
public interface InventoryClient {
    @PostMapping("/api/inventory/reduce")
    Boolean reduceStock(@RequestBody InventoryRequest request);
}
