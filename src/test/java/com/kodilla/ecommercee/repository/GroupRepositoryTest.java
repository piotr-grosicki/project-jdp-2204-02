package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindAll(){
        //given
        Group g1 = new Group("TestName1","Desc");
        Group g2 = new Group("TestName2","Desc");
        repository.save(g1);
        repository.save(g2);
        //when
        List<Group> result = repository.findAll();
        //then
        assertEquals(2,result.size());
        //clenUp
        repository.deleteAll();
    }
    @Test
    public void testFindByGroupId(){
        //given
        Group g1 = new Group("TestName1","Desc");
        Group g2 = new Group( "TestName2","Desc");
        repository.save(g1);
        repository.save(g2);
        //when
        Long id = g1.getGroupId();
        Optional<Group> result = repository.findByGroupId(id);
        //then
        assertTrue(result.isPresent());
        assertEquals(id,result.get().getGroupId());
        //clenUp
        repository.deleteAll();
    }
    @Test
    public void testSave(){
        //given&when
        Group g1 = new Group("TestName1","Desc");
        Group g2 = new Group("TestName2","Desc");
        repository.save(g1);
        repository.save(g2);
        //then
        assertFalse(repository.findAll().isEmpty());
        //clenUp
        repository.deleteAll();
    }
    @Test
    public void testDeleteByGroupId(){
        //given
        Group g1 = new Group("TestName1","Desc");
        Group g2 = new Group("TestName2","Desc");
        repository.save(g1);
        repository.save(g2);
        //when
        Long id = g1.getGroupId();
        repository.deleteByGroupId(id);
        //then
        assertEquals(1,repository.findAll().size());
        assertFalse(repository.findByGroupId(id).isPresent());
        //clenUp
        repository.deleteAll();
    }
    @Test
    public void testAddProductToGroup(){
        //given
        Product p1 = new Product("Laptop",new BigDecimal(999),"Desc");
        Product p2 = new Product("TV",new BigDecimal(1999),"New");
        Product p3 = new Product("Car",new BigDecimal(99999),"4x4");
        Group group = new Group("New Products","Desc");
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        repository.save(group);
        //when
        group.getProducts().add(p1);
        group.getProducts().add(p2);
        group.getProducts().add(p3);
        //then
        assertEquals(3,group.getProducts().size());
        //clenUp
        repository.deleteAll();
        productRepository.deleteAll();

    }
    @Test
    public void testAddAndRemoveProductsFromGroup(){
        //given
        Product p1 = new Product("Laptop",new BigDecimal(999),"Desc");
        Product p2 = new Product("TV",new BigDecimal(1999),"New");
        Product p3 = new Product("Car",new BigDecimal(99999),"4x4");
        Group group = new Group("New Products","Desc");
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        repository.save(group);
        group.getProducts().add(p1);
        group.getProducts().add(p2);
        group.getProducts().add(p3);
        //when
        group.getProducts().remove(p1);
        //then
        assertEquals(2,group.getProducts().size());
        //clenUp
        repository.deleteAll();
        productRepository.deleteAll();
    }
    @Test
    public void testWhatIfIDeleteGroupWithProductsInside(){
        //given
        Product p1 = new Product("Laptop",new BigDecimal(999),"Desc");
        Product p2 = new Product("TV",new BigDecimal(1999),"New");
        Product p3 = new Product("Car",new BigDecimal(99999),"4x4");
        Group group = new Group("New Products","Desc");
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        repository.save(group);
        group.getProducts().add(p1);
        group.getProducts().add(p2);
        group.getProducts().add(p3);
        //when
        repository.deleteByGroupId(group.getGroupId());
        //then
        assertEquals(0,repository.findAll().size());
        assertEquals(3,productRepository.findAll().size());
        //clenUp
        repository.deleteAll();
        productRepository.deleteAll();
    }


}
