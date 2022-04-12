package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy="user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;

    @Column(name = "KEY_ID")
    private String keyId;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @Column(name = "USER_SURNAME")
    private String userSurname;

    public User(String userName, String userSurname) {
        this.userName = userName;
        this.userSurname = userSurname;
    }
}
