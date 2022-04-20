package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.CartNotFoundException;
import com.kodilla.ecommercee.Exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.controller.CartController;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbCartService {
    private final CartRepository cartRepository;
    private final DbProductService productService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    public Cart getCartByUser (User user) {
        return cartRepository.findByUser(user);
    }

    public BigDecimal getCartPrice (final Long id) {
        List<Product> products = cartRepository.findById(id).get().getProducts();
        BigDecimal result = products.stream()
                .map(Product::getProductPrice)
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
        return result;
    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart addNewProductToCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductWithId(productId);
        cart.getProducts().add(product);
        return saveCart(cart);
    }

    public void deleteProduct(Long cartId, int item) throws CartNotFoundException {
        Cart cart = getCartById(cartId);
        cart.getProducts().remove(item);
        saveCart(cart);
    }

    public void clearCart(Long cartId) throws CartNotFoundException {
        Cart cart = getCartById(cartId);
        List<Product> products = cart.getProducts();
        cart.getProducts().removeAll(products);
        saveCart(cart);
    }
}
