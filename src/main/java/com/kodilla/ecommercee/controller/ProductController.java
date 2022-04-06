package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {


    @GetMapping
    public List<ProductDto> getAllProducts(){
        return new ArrayList<>();
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProductById(@PathVariable int productId){
        return new ProductDto();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewProduct(@RequestBody ProductDto productDto){
    }
    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto){

    }
    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable int productId){
    }
}