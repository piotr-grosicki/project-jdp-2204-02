package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
    }

    @PutMapping(value = "block_user/{userId}")
    public void blockUser(@PathVariable int userId) {
    }

    @PutMapping(value="createKey/{userId}")
    public void createKey(@PathVariable int userId){
    }
}
