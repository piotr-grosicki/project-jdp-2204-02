package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.InsufficientPermissionsException;
import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final DbUserService service;
    private final UserMapper userMapper;


    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users=service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "/active_users")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<UserDto>> getAllActiveUsers(){
        List<User> activeUsers= service.getAllBlockedUsers(false);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(activeUsers));
    }

    @GetMapping(value = "/blocked_users")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<UserDto>> getAllBlockedUsers(){
        List<User> blockedUsers= service.getAllBlockedUsers(true);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(blockedUsers));
    }

    @GetMapping(value = "/{userId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        return ResponseEntity.ok(userMapper.mapToUserDto(service.getUserWithId(userId)));
    }


    //admin
    @PutMapping(value = "block_user/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        User updateUser = service.changeStatus(userId, true);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));
    }

    //admin
    @PutMapping(value = "unblock_user/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<UserDto> unblockUser(@PathVariable Long userId) throws UserNotFoundException, InsufficientPermissionsException {
        User updateUser = service.changeStatus(userId, false);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));
    }

    //user
    @PutMapping(value="createKey")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<UserDto> createKey() throws UserNotFoundException, InsufficientPermissionsException {
        User updateUser = service.generateKey();
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));

    }

    //root
    @DeleteMapping(value = "/hardDeleteUser/{userId}")
    @PreAuthorize("hasAuthority('root')")
    public ResponseEntity<Void> hardDeleteUser(@PathVariable Long userId) throws UserNotFoundException {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
