package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;
    private final GroupMapper mapper;

    @GetMapping
    public List<GroupDto> getGroups() {
        List<Group> groups = service.getGroups();
        return mapper.mapToGroupDtoList(groups);
    }

    @GetMapping(value = "{id}")
    public GroupDto getGroup(@PathVariable Long id) throws GroupNotFoundException
    {
        return mapper.mapToGroupDto(service.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        Group group = mapper.mapToGroup(groupDto);
        service.saveGroup(group);
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group group = mapper.mapToGroup(groupDto);
        Group updatedGroup = service.saveGroup(group);
        return mapper.mapToGroupDto(updatedGroup);
    }

    @DeleteMapping(value = "{id}")
    public void deleteGroup(@PathVariable Long id) {
        service.deleteGroup(id);
    }
}