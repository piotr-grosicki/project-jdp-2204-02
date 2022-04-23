package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.Exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.DbGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final DbGroupService service;
    private final GroupMapper groupMapper;

    //all
    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = service.getGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groups));
    }

    //all
    @GetMapping(value = "{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(service.getGroup(id).orElseThrow(GroupNotFoundException::new)));
    }

    //admin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        service.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    //admin
    @PutMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group updatedGroup = service.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(updatedGroup));
    }

    //root
    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('root')")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        service.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}