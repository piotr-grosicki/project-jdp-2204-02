package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.GenericEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {


    @GetMapping
    public List<GenericEntity> getAllProducts(){
        return Arrays.asList(new GenericEntity("Product 1"),
                new GenericEntity("Product 2"));
    }

    @GetMapping(path = "{productId}")
    public GenericEntity getProductById(@PathVariable int productId){
        return new GenericEntity("Product");
    }
    @PostMapping
    public GenericEntity addNewProduct(){
        return new GenericEntity("New");
    }
    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable int productId){

    }
    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable int productId){
    }
}
