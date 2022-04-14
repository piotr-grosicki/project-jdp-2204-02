package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {

    public Order(String address, LocalDate now) {
        this.address = address;
        this.created = now;
    }


    public Order(User user, String address, LocalDate now) {
        this.user = user;
        this.address = address;
        this.created = now;
    }

    public Order(User user, Cart cart, String address, LocalDate now) {
        this.user = user;
        this.cart = cart;
        this.address = address;
        this.created = now;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(mappedBy = "order")
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @NotNull
    @Column(name = "DATE_CREATED")
    private LocalDate created;
}
