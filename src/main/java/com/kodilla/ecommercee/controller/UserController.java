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
import java.util.UUID;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final DbUserService service;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users=service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "/active_users")
    public ResponseEntity<List<UserDto>> getAllActiveUsers(){
        Boolean blocked = false;
        List<User> activeUsers= service.getAllBlockedUsers(blocked);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(activeUsers));
    }

    @GetMapping(value = "/blocked_users")
    public ResponseEntity<List<UserDto>> getAllBlockedUsers(){
        Boolean blocked=true;
        List<User> blockedUsers= service.getAllBlockedUsers(blocked);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(blockedUsers));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user= userMapper.mapToUser(userDto);
        service.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "block_user/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        User user=service.getUserWithId(userId);
        user.setUserBlocked(true);
        User updateUser=service.addUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));
    }

    @PutMapping(value="createKey/{userId}")
    public ResponseEntity<UserDto> createKey(@PathVariable Long userId) throws UserNotFoundException {
        User user= service.getUserWithId(userId);
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setKeyId(uuid);
        User updateUser=service.addUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(updateUser));

    }
}
