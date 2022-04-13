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

    public User(String userName, String userSurname) {
        this.userName = userName;
        this.userSurname = userSurname;
    }

    public User(Long id, String userName, String userSurname) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
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
