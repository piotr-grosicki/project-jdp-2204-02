package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
//import com.kodilla.ecommercee.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    Optional<Order> findByOrderId(Long orderId);

    //TODO: odkomentowac po powstaniu encji User
    //List<Order> findByUserId(Optional<User> user);

    Order save(Order order);
    void deleteByOrderId(Long orderId);
}
