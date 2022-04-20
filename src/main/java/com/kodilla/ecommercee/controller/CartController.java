package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.CartNotFoundException;
import com.kodilla.ecommercee.Exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.DbCartService;
import com.kodilla.ecommercee.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final DbCartService service;
    private final CartMapper cartMapper;
    private final DbUserService userService;

    //root
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    //user
    @GetMapping(value = "{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId) throws UserNotFoundException {
        User user = userService.getUserWithId(userId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(service.getCartByUser(user)));
    }

    //admin
    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        List<Cart> carts = service.getAllCarts();
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(carts));
    }

    //admin
    @PutMapping
    public ResponseEntity<CartDto> editCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        Cart editedCart = service.saveCart(cart);
        return ResponseEntity.ok(cartMapper.mapToCartDto(editedCart));
    }

    //user
    @PutMapping(value = "/{cartId}/add/{productId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart updatedCart = service.addNewProductToCart(cartId, productId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(updatedCart));
    }

    //user
    @DeleteMapping(value = "/{cartId}/delete/{item}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId, @PathVariable int item) throws CartNotFoundException {
        service.deleteProduct(cartId, item);
        return ResponseEntity.ok().build();
    }

    //user
    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) throws CartNotFoundException {
        service.clearCart(cartId);
        return ResponseEntity.ok().build();
    }

}