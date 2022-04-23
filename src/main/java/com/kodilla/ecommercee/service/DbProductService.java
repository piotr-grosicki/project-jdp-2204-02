package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.Exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbProductService {

    @Autowired
    private final ProductRepository repository;

    @Autowired
    private final DbGroupService groupService;

    @Autowired
    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(final Long Id)throws ProductNotFoundException {
        return repository.findById(Id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public void deleteProduct(final Long productId) {
        repository.deleteByProductId(productId);
    }

    public List<Product> getAllProductsByGroup(final Group group) {
        return repository.findByGroup(group);
    }

    public void addProductToGroup(ProductDto productDto, Long groupId) throws GroupNotFoundException {
        Optional<Group> group = groupService.getGroup(groupId);
        Product product = productMapper.mapToProduct(productDto);
        product.setGroup(group.get());
        saveProduct(product);
    }
}
