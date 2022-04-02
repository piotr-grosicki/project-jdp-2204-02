package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.GenericEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<GenericEntity> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public GenericEntity getOrder(Long orderId) {
        return new GenericEntity();
    }

    @GetMapping(value = "/user/{userId}")
    public List<GenericEntity> getUserOrders(Long userId) {
        return new ArrayList<>();
    }

    @PostMapping
    public void createOrder(GenericEntity genericEntity) {
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(Long orderId) {
    }
}