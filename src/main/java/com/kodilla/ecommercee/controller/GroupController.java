package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
@RequiredArgsConstructor
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{id}")
    public void getGroup(@PathVariable Long id) {

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping
    public void updateGroup(@RequestBody GroupDto groupDto) {

    }

    @DeleteMapping(value = "{id}")
    public void deleteGroup(@PathVariable Long id) {
    }
}