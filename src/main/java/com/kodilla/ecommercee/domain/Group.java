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
    @NotNull
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private Long groupId;
//TODO: odkomentować po złączeniu z encją Product
//    @OneToMany(
//            targetEntity = Product.class,
//            mappedBy = "productId",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Product> products;

    @NotNull
    @Column(name = "GROUP_NAME")
    private String groupName;

    @NotNull
    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;


}
