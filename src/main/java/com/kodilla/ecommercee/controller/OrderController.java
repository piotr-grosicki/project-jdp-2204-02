package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(Long orderId) {
        return new OrderDto();
    }

    @GetMapping(value = "/user/{userId}")
    public List<OrderDto> getUserOrders(Long userId) {
        return new ArrayList<>();
    }

    @PostMapping
    public void createOrder(OrderDto orderDto) {
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(Long orderId) {
    }
}