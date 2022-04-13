package com.kodilla.ecommercee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class GroupDto {

    private Long groupId;
    private String groupName;
    private String groupDescription;

    public GroupDto(Long id, String name, String description) {
        this.groupId = id;
        this.groupName = name;
        this.groupDescription = description;
    }
}
