package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.*;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.DbOrderService;
import com.kodilla.ecommercee.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final DbOrderService orderService;
    private final OrderMapper orderMapper;
    private final DbUserService userService;


    //admin
    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    //admin
    @GetMapping(value = "/active")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<OrderDto>> getActiveOrders() {
        List<Order> orders = orderService.getOrdersByStatus(false);
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    //admin
    @GetMapping(value = "/canceled")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<OrderDto>> getCanceledOrders() {
        List<Order> orders = orderService.getOrdersByStatus(true);
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    //user TODO: użytkownik powinien móc sprawdzić tylko swoje zamówienie,a admin każde
    @GetMapping(value = "/{orderId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.getOrderById(orderId)));
    }

    @GetMapping(value = "/user={userId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        User user = userService.getUserWithId(userId);
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList((orderService.getOrdersByUser(user))));
    }

    //user
    @PostMapping(value = "/create_order/user={userId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Void> createOrder(@PathVariable Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        orderService.createNewOrder(userId);
        return ResponseEntity.ok().build();
    }

    //admin
    @PutMapping(value = "/cancel_order={orderId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        orderService.cancelThisOrder(orderId);
        return ResponseEntity.ok().build();
    }

    //root
    @DeleteMapping(value = "{orderId}")
    @PreAuthorize("hasAuthority('root')")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}