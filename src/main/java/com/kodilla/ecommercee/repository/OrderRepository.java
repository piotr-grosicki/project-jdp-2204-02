package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    Optional<Order> findByOrderId(Long orderId);
    List<Order> findByUserId(Long userId);
    Order save(Order order);
    void deleteByOrderId(Long orderId);
}
