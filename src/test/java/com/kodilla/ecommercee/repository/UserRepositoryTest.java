package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testUserSave(){

        //Given
        User user= new User("Jacob", "Johnson");

        //When
        userRepository.save(user);
        Long id= user.getUserId();
        Optional<User> readUser= userRepository.findByUserId(id);

        //Then
        assertTrue(readUser.isPresent());

        //CleanUp
        userRepository.deleteByUserId(id);
    }

    @Test
    public void testAddNewUser() {

        //Given
        User user1= new User("Czeslaw", "Kowalski");

        //When
        userRepository.save(user1);
        Long id= user1.getUserId();

        //Then
        assertEquals(1,userRepository.findAll().size());

        //Clean up
        userRepository.deleteByUserId(id);
    }

    @Test
    public void testDeleteByUserId() {

        //Given
        User user1= new User("Czeslaw", "Kowalski");
        User user2= new User ("Nicola", "Smith");
        userRepository.save(user1);
        userRepository.save(user2);
        Long id1= user1.getUserId();
        Long id2= user2.getUserId();

        //When
        userRepository.deleteByUserId(id1);
        userRepository.deleteByUserId(id2);

        //Then
        assertEquals(0,userRepository.findAll().size());
    }

    @Test
    public void testUserExistWhenOrderDelete(){

        //Given
        User user1= new User("Czeslaw", "Kowalski");
        User user2= new User ("Nicola", "Smith");
        Order order= new Order(user1,"Address", LocalDate.now());

        //When
        userRepository.save(user1);
        userRepository.save(user2);
        orderRepository.save(order);
        orderRepository.deleteAll();

        Long id1= user1.getUserId();
        Long id2= user2.getUserId();
        Long id3= order.getOrderId();

        //Then
        assertEquals(2, userRepository.findAll().size());

        //Clean up
        userRepository.deleteByUserId(id1);
        userRepository.deleteByUserId(id2);
        orderRepository.deleteByOrderId(id3);
    }

    @Test
    public void testUserBlocked(){

        //Given
        User user1= new User("Mary", "Cold");
        User user2= new User("John", "Biden");
        User user3= new User("Katy", "Melua");
        User user4= new User("King", "Broo");
        User user5= new User("Wally", "Tomato");

        //When
        user1.setUserBlocked(true);
        user2.setUserBlocked(true);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //Then
        assertEquals(2, userRepository.findByUserBlocked(true).size());
        assertEquals(3, userRepository.findByUserBlocked(false).size());
        assertEquals(5, userRepository.findAll().size());

        //Clean up
        userRepository.deleteAll();
    }
}
