package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/getGroup/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return new GroupDto();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto();
    }

    @DeleteMapping("deleteGroup")
    public void deleteGroup(@RequestParam Long id) {
    }
}
