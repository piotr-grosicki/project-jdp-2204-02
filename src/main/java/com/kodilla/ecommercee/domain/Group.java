package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`GROUPS`")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private Long groupId;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    @NotNull
    @Column(name = "GROUP_NAME")
    private String groupName;

    @NotNull
    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;

    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
}
