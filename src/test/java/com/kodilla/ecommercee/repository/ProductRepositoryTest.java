package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void addNewProduct() {
        //Given
        Product product1 = new Product("Product1", new BigDecimal(100), "Desc1");

        //When
        productRepository.save(product1);

        //Then
        assertEquals(1, productRepository.findAll().size());

        //Clean up
        productRepository.deleteAll();
    }

    @Test
    public void getProductById() {
        //Given
        Product product1 = new Product("Product1", new BigDecimal(100), "Desc1");

        //When
        productRepository.save(product1);
        Optional<Product> foundProduct = productRepository.findById(product1.getProductId());

        //Then
        assertEquals(product1.getProductId(), foundProduct.get().getProductId());

        //Clean up
        productRepository.deleteAll();
    }

    @Test
    public void addNewProductWithGroup() {
        //Given
        Group group = new Group("Desc1");

        Product product1 = new Product(group, "Product1", new BigDecimal(100), "Desc1");
        Product product2 = new Product(group, "Product2", new BigDecimal(100), "Desc2");

        //When
        groupRepository.save(group);

        productRepository.save(product1);
        productRepository.save(product2);

        //Then
        List<Product> proListOfGroup = productRepository.findAll();
        assertEquals(2, proListOfGroup.size());

        //Clean up
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void getProductListByGroupName() {
        //Given
        Group group = new Group( "Desc1");
        Group group2 = new Group( "Desc2");

        Product product1 = new Product(group, "Product1", new BigDecimal(100), "Desc1");
        Product product2 = new Product(group, "Product2", new BigDecimal(100), "Desc2");
        Product product3 = new Product(group2, "Product3", new BigDecimal(100), "Desc3");
        Product product4 = new Product(group2, "Product4", new BigDecimal(100), "Desc4");

        //When
        groupRepository.save(group);
        groupRepository.save(group2);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        //Then
        List<Product> proListOfG1 = productRepository.findByGroup(group);
        assertEquals(2, proListOfG1.size());

        //Clean up
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void addProductToGroup() {
        //Given
        Group group = new Group("Desc1");
        Product product1 = new Product("Product1", new BigDecimal(100), "Desc1");

        //When
        groupRepository.save(group);

        productRepository.save(product1);

        group.getProducts().add(product1);

        product1.setGroup(group);

        //Then
        assertEquals(1, group.getProducts().size());
        assertEquals(group.getId() , product1.getGroup().getId());

        //Clean up
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void deleteProductById() {
        //Given
        Group group = new Group("Desc1");
        Product product = new Product("Product1", new BigDecimal(100), "Desc1");

        groupRepository.save(group);
        productRepository.save(product);

        //When
        Long deleteId = product.getProductId();
        productRepository.deleteByProductId(deleteId);

        //Then
        assertEquals(0, productRepository.findAll().size());
    }

    @Test
    public void deleteProductByIdFromGroup() {
        //Given
        Group group = new Group("Desc1");
        Product product = new Product(group,"Product1", new BigDecimal(100), "Desc1");

        groupRepository.save(group);
        productRepository.save(product);

        //When
        Long deleteId = product.getProductId();
        productRepository.deleteByProductId(deleteId);

        //Then
        assertEquals(0, productRepository.findAll().size());
    }

    @Test
    public void addProductToCart() {
        //Given
        Product product = new Product("Product1", new BigDecimal(100), "Desc1");
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
}