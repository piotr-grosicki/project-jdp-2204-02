package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testAddNewCart() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);

        //Then
        assertEquals(1, cartRepository.findAll().size());

        //Clean up
        cartRepository.deleteAll();
    }

    @Test
    public void testGetCartById() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Optional<Cart> foundCart = cartRepository.findById(cart.getId());

        //Then
        assertEquals(cart.getId(), foundCart.get().getId());

        //Clean up
        cartRepository.deleteAll();
    }

    @Test
    public void testAddProductToCart() {
        //Given
        Product product = new Product("Product1", new BigDecimal(100), "Description");
        Cart cart = new Cart();

        cartRepository.save(cart);
        productRepository.save(product);

        //When
        cart.getProducts().add(product);

        //Then
        assertEquals(1, cart.getProducts().size());

        //Clean up
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testDeleteProductFromCart() {
        //Given
        Product product1 = new Product("Product1", new BigDecimal(100), "Description");
        Product product2 = new Product("Product2", new BigDecimal(120), "Description");
        Cart cart = new Cart();

        cartRepository.save(cart);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        cart.getProducts().remove(product1);
        //Then
        assertEquals(1, cartRepository.findAll().size());

        //Clean up
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testDeleteCartById() {
        //Given
        Cart cart = new Cart();
        cartRepository.save(cart);

        //When
        Long cartId = cart.getId();
        cartRepository.deleteById(cartId);

        //Then
        assertEquals(0, cartRepository.findAll().size());
    }
}
