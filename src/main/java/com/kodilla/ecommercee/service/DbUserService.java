package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Exceptions.InsufficientPermissionsException;
import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.enums.AppRoles;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.kodilla.ecommercee.enums.AppRoles.*;

@Service
@RequiredArgsConstructor
public class DbUserService implements UserDetailsService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findByUsername(String username){
        return repository.findByUsername(username);
    }

    public User addUser(final User user) {
        return repository.save(user);
    }

    public User getUserWithId(final Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        Optional<User> loggedInUser = repository.findByUsername(currentLoggedInUser());
        if (loggedInUser.get().getUserId() == userId || loggedInUser.get().getAuthorities().equals(ADMIN.getGrantedAuthority()) ||
        loggedInUser.get().getAuthorities().equals(ROOT.getGrantedAuthority())) {
            return repository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
        }else{
            throw new InsufficientPermissionsException();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("Username %s not found",username)
                ));
    }

    public String currentLoggedInUser(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }else{
            username = principal.toString();
        }
        return username;
    }
    public Long getCurrentLoggedInUserId(){
        return findByUsername(currentLoggedInUser()).get().getUserId();
    }

    public void deleteUser(final Long userId){
        repository.deleteByUserId(userId);}

    public List<User> getAllBlockedUsers(final Boolean blocked){
        return repository.findByUserBlocked(blocked);}

    public void createNewUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        addUser(user);
    }

    public User generateKey() throws UserNotFoundException, InsufficientPermissionsException {
        User user= getUserWithId(getCurrentLoggedInUserId());
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setKeyId(uuid);
        addUser(user);
        return user;
    }

    public User changeStatus(Long userId, boolean b) throws UserNotFoundException, InsufficientPermissionsException {
        User user = getUserWithId(userId);
        user.setUserBlocked(b);
        return addUser(user);
    }
}
