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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne
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

    public Order(String address, LocalDate created) {
        this.address = address;
        this.created = created;
    }

    public Order(User user, String address, LocalDate created) {
        this.user = user;
        this.address = address;
        this.created = created;
    }

    public Order(User user, Cart cart, String address, LocalDate created) {
        this.user = user;
        this.cart = cart;
        this.address = address;
        this.created = created;
    }
}
