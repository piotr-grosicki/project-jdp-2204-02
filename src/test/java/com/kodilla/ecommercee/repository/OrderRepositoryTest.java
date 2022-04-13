package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void addNewOrder() {
        //Given
        Order testOrder = new Order("testAddress", LocalDate.now());
        //When
        orderRepository.save(testOrder);
        //Then
        assertEquals(1, orderRepository.findAll().size());
        //Clean up
        orderRepository.deleteAll();
    }
    @Test
    public void testFindAllOrders(){
        //Given
        Order testOrder = new Order("testAddress", LocalDate.now());
        Order testOrder2 = new Order("testAddress", LocalDate.now());
        Order testOrder3 = new Order("testAddress", LocalDate.now());

        //When
        orderRepository.save(testOrder);
        orderRepository.save(testOrder2);
        orderRepository.save(testOrder3);

        //Then
        assertEquals(3, orderRepository.findAll().size());

        //Clean up
        orderRepository.deleteAll();
    }

    @Test
    public void testFindByOrderId() {
        Order testOrder = new Order("testAddress", LocalDate.now());
        //When
        orderRepository.save(testOrder);
        Optional<Order> testOrderId = orderRepository.findById(testOrder.getId());
        //Then
        assertEquals(testOrder.getId(), testOrderId.get().getId());
        //Clean up
        orderRepository.deleteAll();
    }
    @Test
    public void findOrdersByUser(){
        //Given
        User testUser = new User("Name");

        Order testOrder = new Order(testUser, "address", LocalDate.now());
        Order testOrder2 = new Order(testUser, "address", LocalDate.now());

        //When
        userRepository.save(testUser);

        orderRepository.save(testOrder);
        orderRepository.save(testOrder2);

        //Then
        List<Order> testListOfOrders = new ArrayList<>();

        testListOfOrders.add(testOrder);
        testListOfOrders.add(testOrder2);

        assertEquals(testListOfOrders.size(), orderRepository.findByUser(testUser).size());

        //Clean up
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void findUserForOrder(){
        //Given
        User testUser = new User("User");
        Cart testCart = new Cart();
        Order testOrder = new Order(testUser, testCart, "address", LocalDate.now());

        //When
        userRepository.save(testUser);
        cartRepository.save(testCart);
        orderRepository.save(testOrder);

        //Then
        assertEquals(testUser, testOrder.getUser());

        //Clean up
        userRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void deleteOrderById(){
        //Given
        User testUser = new User("User");
        Cart testCart = new Cart();
        Order testOrder = new Order(testUser, testCart, "address", LocalDate.now());

        //When
        userRepository.save(testUser);
        cartRepository.save(testCart);
        orderRepository.save(testOrder);

        //Then
        assertEquals(1, orderRepository.findAll().size());

        orderRepository.deleteById(testOrder.getId());

        assertEquals(0, orderRepository.findAll().size());
        assertEquals(1, cartRepository.findAll().size());
        assertEquals(1, userRepository.findAll().size());

        //Clean up
        userRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void findCartForOrder(){
        //Given
        User testUser = new User("User");
        Cart testCart = new Cart();
        Order testOrder = new Order(testUser, testCart, "address", LocalDate.now());

        //When
        userRepository.save(testUser);
        cartRepository.save(testCart);
        orderRepository.save(testOrder);

        //Then
        assertEquals(testCart, testOrder.getCart());

        //Clean up
        userRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();
    }
}