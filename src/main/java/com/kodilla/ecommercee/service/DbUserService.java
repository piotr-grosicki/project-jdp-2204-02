package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DbUserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User addUser(final User user) {
        return repository.save(user);
    }

    public User getUserWithId(final Long userId) throws UserNotFoundException {
        return repository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(final Long userId){
        repository.deleteByUserId(userId);}

    public List<User> getAllBlockedUsers(final Boolean blocked){
        return repository.findByUserBlocked(blocked);}

    public void createNewUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        cartRepository.save(cart);
        addUser(user);
    }

    public User generateKey(Long userId) throws UserNotFoundException {
        User user= getUserWithId(userId);
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setKeyId(uuid);
        addUser(user);
        return user;
    }

    public User changeStatus(Long userId, boolean b) throws UserNotFoundException {
        User user = getUserWithId(userId);
        user.setUserBlocked(b);
        return addUser(user);
    }
}
