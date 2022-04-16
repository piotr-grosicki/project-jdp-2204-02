package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.*;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.DbCartService;
import com.kodilla.ecommercee.service.DbOrderService;
import com.kodilla.ecommercee.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final DbOrderService orderService;
    private final OrderMapper orderMapper;
    private final DbUserService userService;
    private final DbCartService cartService;


    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping(value = "/active")
    public ResponseEntity<List<OrderDto>> getActiveOrders() {
        List<Order> orders = orderService.getOrdersByStatus(false);
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping(value = "/canceled")
    public ResponseEntity<List<OrderDto>> getCanceledOrders() {
        List<Order> orders = orderService.getOrdersByStatus(true);
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.getOrderById(orderId)));
    }

    @GetMapping(value = "/user={userId}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable Long userId) throws UserNotFoundException {
        User user = userService.getUserWithId(userId);
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList((orderService.getOrdersByUser(user))));
    }

    @PostMapping(value = "/create_order/user={userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@PathVariable Long userId, @RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        User user = userService.getUserWithId(userId);
        Cart cart =cartService.getCartById(user.getUserId());
        Order order = orderMapper.mapToOrder(orderDto);
        order.setUser(user);
        order.setProducts(cart.getProducts());
        orderService.saveOrder(order);
        cart.getProducts().clear();
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/cancel_order={orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        order.setOrderCanceled(true);
        orderService.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}