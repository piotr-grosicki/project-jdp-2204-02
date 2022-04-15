package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Order order;
    private User user;
    private BigDecimal cartPrice;
    private List<Product> products;
}
