package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final DbUserService service;
    private final UserMapper userMapper;

    //admin
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users=service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    //admin
    @GetMapping(value = "/active_users")
    public ResponseEntity<List<UserDto>> getAllActiveUsers(){
        List<User> activeUsers= service.getAllBlockedUsers(false);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(activeUsers));
    }

    //admin
    @GetMapping(value = "/blocked_users")
    public ResponseEntity<List<UserDto>> getAllBlockedUsers(){
        List<User> blockedUsers= service.getAllBlockedUsers(true);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(blockedUsers));
    }

    //user
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(service.getUserWithId(userId)));
    }

    //all
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        service.createNewUser(userDto);
        return ResponseEntity.ok().build();
    }

    //user
    @PutMapping(value = "block_user/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        User updateUser = service.changeStatus(userId, true);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));
    }

    //admin
    @PutMapping(value = "unblock_user/{userId}")
    public ResponseEntity<UserDto> unblockUser(@PathVariable Long userId) throws UserNotFoundException {
        User updateUser = service.changeStatus(userId, false);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));
    }

    //user
    @PutMapping(value="createKey/{userId}")
    public ResponseEntity<UserDto> createKey(@PathVariable Long userId) throws UserNotFoundException {
        User updateUser = service.generateKey(userId);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));

    }

    //root
    @DeleteMapping(value = "/hardDeleteUser/{userId}")
    public ResponseEntity<Void> hardDeleteUser(@PathVariable Long userId) throws UserNotFoundException {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
