package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.service.DbOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private DbOrderService orderService;

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getUser(),
                orderDto.getAddress(),
                orderDto.getCreated(),
                orderDto.getProducts(),
                orderDto.getStatus());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser(),
                order.getAddress(),
                order.getCreated(),
                order.getProducts(),
                orderService.getOrderPrice(order.getId()),
                order.getOrderCanceled());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}