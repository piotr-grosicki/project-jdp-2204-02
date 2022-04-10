package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductWithId(final Long productId)throws ProductNotFoundException {
        return repository.findByProductId(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public void deleteProduct(final Long productId) {
        repository.deleteByProductId(productId);
    }
}
