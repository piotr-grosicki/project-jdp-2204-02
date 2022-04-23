package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final DbUserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        service.createNewUser(userDto);
        return ResponseEntity.ok().build();
    }
}
