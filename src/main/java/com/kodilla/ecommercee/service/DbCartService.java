package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.CartNotFoundException;
import com.kodilla.ecommercee.Exceptions.InsufficientPermissionsException;
import com.kodilla.ecommercee.Exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbCartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final DbProductService productService;

    @Autowired
    private final DbUserService userService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(final Long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public BigDecimal getCartPrice(final Long id) {
        List<Product> products = cartRepository.findById(id).get().getProducts();
        BigDecimal result = products.stream()
                .map(Product::getProductPrice)
                .reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
        return result;

    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart addNewProductToCart(Long userId, Long productId) throws ProductNotFoundException, UserNotFoundException, InsufficientPermissionsException {
        Cart cart = getCartByUser(userService.getUserWithId(userId));
        Product product = productService.getProductById(productId);
        cart.getProducts().add(product);
        return saveCart(cart);
    }

    public void deleteProduct(Long userId, Long productId) throws UserNotFoundException, ProductNotFoundException, InsufficientPermissionsException {
        Cart cart = getCartByUser(userService.getUserWithId(userId));
        Product product = cart.getProducts().stream()
                .filter(p->p.getProductId().equals(productId))
                .findFirst().orElseThrow(ProductNotFoundException::new);

        cart.getProducts().remove(product);
        saveCart(cart);
    }

    public void clearCart(Long cartId) throws CartNotFoundException {
        Cart cart = getCartById(cartId);
        List<Product> products = cart.getProducts();
        cart.getProducts().removeAll(products);
        saveCart(cart);
    }
}
