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
        User user = new User("Name", "Surname");

        //When
        userRepository.save(user);
        Long id= user.getUserId();
        Optional<User> readUser= userRepository.findById(id);

        //Then
        assertTrue(readUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testAddNewUser() {

        //Given
        User user1= new User("Name", "Surname");

        //When
        userRepository.save(user1);
        Long id= user1.getUserId();

        //Then
        assertEquals(1,userRepository.findAll().size());

        //Clean up
        userRepository.deleteById(id);
    }

    @Test
    public void testDeleteByUserId() {

        //Given
        User user1= new User("Name", "Surname");
        User user2= new User ("Name2", "Surname2");
        userRepository.save(user1);
        userRepository.save(user2);
        Long id1= user1.getUserId();
        Long id2= user2.getUserId();

        //When
        userRepository.deleteById(id1);
        userRepository.deleteById(id2);

        //Then
        assertEquals(0,userRepository.findAll().size());
    }

    @Test
    public void testUserExistWhenOrderDelete()
    {

        //Given
        User user1 = new User("Name", "Surname");
        User user2 = new User("Name2", "Surname2");
        Order order = new Order();

        //When
        userRepository.save(user1);
        userRepository.save(user2);

        orderRepository.save(order);

        orderRepository.deleteAll();

        Long id1 = user1.getUserId();
        Long id2 = user2.getUserId();


        //Then
        assertEquals(2, userRepository.findAll().size());

        //Clean up
        userRepository.deleteById(id1);
        userRepository.deleteById(id2);
    }

    @Test
    public void testUserBlocked() {

        //Given
        User user1 = new User("Mary", "Cold");
        User user2 = new User("John", "Biden");
        User user3 = new User("Katy", "Melua");
        User user4 = new User("King", "Broo");
        User user5 = new User("Wally", "Tomato");

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

    @Test
    public void cleanUp() {
        userRepository.deleteAll();
    }
}
