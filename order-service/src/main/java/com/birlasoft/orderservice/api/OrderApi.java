package com.birlasoft.orderservice.api;

import com.birlasoft.orderservice.models.OrderResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order-service/V1")
public interface OrderApi {
    @PostMapping("/order/{userId}")
    OrderResponse order(@PathVariable Long userId);
}
