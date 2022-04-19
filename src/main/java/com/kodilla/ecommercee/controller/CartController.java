package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.CartNotFoundException;
import com.kodilla.ecommercee.Exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.DbCartService;
import com.kodilla.ecommercee.service.DbProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final DbCartService service;
    private final DbProductService productService;
    private final CartMapper cartMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(service.getCartById(cartId)));
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        List<Cart> carts = service.getAllCarts();
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(carts));
    }

    @PutMapping
    public ResponseEntity<CartDto> editCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        Cart editedCart = service.saveCart(cart);
        return ResponseEntity.ok(cartMapper.mapToCartDto(editedCart));
    }

    @PutMapping(value = "/{cartId}/add/{productId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = service.getCartById(cartId);
        Product product = productService.getProductWithId(productId);
        cart.getProducts().add(product);
        Cart updatedCart = service.saveCart(cart);
        return ResponseEntity.ok(cartMapper.mapToCartDto(updatedCart));
    }

    @DeleteMapping(value = "/delete/{cartId}/{item}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId, @PathVariable int item) throws CartNotFoundException {
        Cart cart = service.getCartById(cartId);
        cart.getProducts().remove(item);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = service.getCartById(cartId);
        List<Product> products = cart.getProducts();
        cart.getProducts().removeAll(products);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }
}