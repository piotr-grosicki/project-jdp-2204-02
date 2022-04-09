package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="USER_ID")
    private Long userId;
    /* Połączenie z encją Order
    @OneToMany(
            targetEntity = Order.class,
            mappedBy="orderId"
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;
    */

    @NotNull
    @Column(name = "KEY_ID")
    private Long keyId;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @Column(name = "USER_SURNAME")
    private String userSurname;

}
