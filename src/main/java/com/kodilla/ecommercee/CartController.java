package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @GetMapping(value = "{cartId}")
    public CartDto getCart(@PathVariable Long cartId) {
        return new CartDto();
    }

    @GetMapping
    public List<CartDto> getAllCarts() {
        return new ArrayList<>();
    }

    @PutMapping
    public CartDto editCart(@RequestBody CartDto cartDto) {
        return new CartDto();
    }

    @DeleteMapping(value = "{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
    }
}

