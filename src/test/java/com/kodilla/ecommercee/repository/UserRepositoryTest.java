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
        User user = new User("User");

        //When
        userRepository.save(user);
        Long id= user.getId();
        Optional<User> readUser= userRepository.findById(id);

        //Then
        assertTrue(readUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testAddNewUser() {

        //Given
        User user1= new User("User");

        //When
        userRepository.save(user1);
        Long id= user1.getId();

        //Then
        assertEquals(1,userRepository.findAll().size());

        //Clean up
        userRepository.deleteById(id);
    }

    @Test
    public void testDeleteByUserId() {

        //Given
        User user1= new User("User");
        User user2= new User ("User2");
        userRepository.save(user1);
        userRepository.save(user2);
        Long id1= user1.getId();
        Long id2= user2.getId();

        //When
        userRepository.deleteById(id1);
        userRepository.deleteById(id2);

        //Then
        assertEquals(0,userRepository.findAll().size());
    }

    @Test
    public void testUserExistWhenOrderDelete(){

        //Given
        User user1= new User("User");
        User user2= new User ("User2");
        Order order= new Order("testAddress", LocalDate.now());

        //When
        userRepository.save(user1);
        userRepository.save(user2);

        orderRepository.save(order);

        orderRepository.deleteAll();

        Long id1= user1.getId();
        Long id2= user2.getId();


        //Then
        assertEquals(2, userRepository.findAll().size());

        //Clean up
        userRepository.deleteById(id1);
        userRepository.deleteById(id2);

    }
}
