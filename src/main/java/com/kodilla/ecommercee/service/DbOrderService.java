package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbOrderService {

    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public List<Order> getOrdersByUser(User user) {
        return repository.findByUser(user);
    }

    public Order getOrderById(final Long id) throws OrderNotFoundException {
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order) {
        return repository.save(order);
    }

    public void deleteOrder(final Long id) {
        repository.deleteById(id);
    }

    public List<Order> getOrdersByStatus(final Boolean status) {
        return repository.findAllByOrderCanceled(status);
    }

    public BigDecimal getOrderPrice (final Long id) {
        List<Product> products = repository.findById(id).get().getProducts();
        BigDecimal result = products.stream()
                .map(Product::getProductPrice)
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
        return result;
    }
}