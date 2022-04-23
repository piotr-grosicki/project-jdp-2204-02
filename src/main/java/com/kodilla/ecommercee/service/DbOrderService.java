package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.InsufficientPermissionsException;
import com.kodilla.ecommercee.Exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
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
    private final DbUserService userService;
    private  final DbCartService cartService;

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

    public void createNewOrder(Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        User user = userService.getUserWithId(userId);
        Cart cart = cartService.getCartByUser(user);
        Order order = new Order();
        order.setUser(user);
        for (Product pro: cart.getProducts()) {
            order.getProducts().add(pro);
        }
        saveOrder(order);
        cart.getProducts().clear();
        cartService.saveCart(cart);
    }

    public void cancelThisOrder(Long orderId) throws OrderNotFoundException {
        Order order = getOrderById(orderId);
        order.setOrderCanceled(true);
        saveOrder(order);
    }
}