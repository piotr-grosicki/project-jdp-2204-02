package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    public User(String userName) {
        this.userName = userName;
    }

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_SURNAME")
    private String userSurname;

    @Column(name = "KEY_ID")
    private String keyId;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy="user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;

}
