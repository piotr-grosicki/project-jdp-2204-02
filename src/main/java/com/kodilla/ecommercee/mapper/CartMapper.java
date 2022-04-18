package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.service.DbCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartMapper {

    private DbCartService cartService;

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getId(),
                cartDto.getUser(),
                cartDto.getProducts());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser(),
                cartService.getCartPrice(cart.getId()),
                cart.getProducts());
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> carts) {
        List<CartDto> cartDtoList = new ArrayList<>();
        for (Cart cart : carts) {
            cartDtoList.add(new CartDto(
                    cart.getId(),
                    cart.getUser(),
                    cartService.getCartPrice(cart.getId()),
                    cart.getProducts()));
        }
        return cartDtoList;
    }
}
