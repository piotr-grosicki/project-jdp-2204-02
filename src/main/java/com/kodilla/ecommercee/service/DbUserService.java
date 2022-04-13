package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exeptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbUserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User addUser(final User user) {
        return repository.save(user);
    }
    public User getUserWithId(final Long userId)throws UserNotFoundException {
        return repository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(final Long userId){
        repository.deleteByUserId(userId);}
}
