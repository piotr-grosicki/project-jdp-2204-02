package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();
    Optional<Product> findByProductId(Long productId);
    Product save(Product product);
    void deleteByProductId(Long productId);
    List<Product> findByGroup(Group group);
}
