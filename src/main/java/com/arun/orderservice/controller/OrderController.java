package com.arun.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arun.orderservice.model.Order;
import com.arun.orderservice.repository.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/update")
    public String updateOrder(@RequestHeader("deviceId") String deviceId, @RequestBody Order order) {
        order.setDeviceId(deviceId);
        orderRepository.save(order);
        return "Order updated with device ID: " + deviceId;
    }
}