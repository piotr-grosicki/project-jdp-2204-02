package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public OrderDto getOrder(@PathVariable Long orderId) {
        return new OrderDto();
    }

    @GetMapping(value = "/user/{userId}")
    public List<OrderDto> getUserOrders(@PathVariable Long userId) {
        return new ArrayList<>();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
    }

    @PutMapping
    public void editOrder(@RequestBody OrderDto orderDto) {
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
    }
}
