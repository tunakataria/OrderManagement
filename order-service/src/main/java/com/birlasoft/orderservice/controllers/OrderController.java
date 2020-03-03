package com.birlasoft.orderservice.controllers;

import com.birlasoft.orderservice.api.OrderApi;
import com.birlasoft.orderservice.models.OrderResponse;
import com.birlasoft.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;
    @Override
    public OrderResponse order(Long userId) {
        return orderService.order(userId);
    }
}
