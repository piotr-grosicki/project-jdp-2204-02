package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.service.DbOrderService;
import com.kodilla.ecommercee.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private DbOrderService orderService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DbUserService userService;

    public Order mapToOrder(OrderDto orderDto) throws UserNotFoundException {
        return new Order(
                orderDto.getId(),
                userService.getUserWithId(orderDto.getUserId()),
                orderDto.getCreated(),
                productMapper.mapToProductList(orderDto.getProducts()),
                orderDto.getStatus());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getUserId(),
                order.getCreated(),
                productMapper.mapToProductDtoList(order.getProducts()),
                orderService.getOrderPrice(order.getId()),
                order.getOrderCanceled());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}