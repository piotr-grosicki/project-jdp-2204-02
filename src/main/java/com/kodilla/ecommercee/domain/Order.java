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
    @NotNull
    @Column(name = "ORDER_ID")
    private Long orderId;

    //TODO: odkomentowac po powstaniu encji User
    //@OneToMany(mappedBy = "id")
    //private User userId;

    //TODO: odkomentowac po powstaniu encji Cart
    //@OneToOne(mappedBy = "id")
    //private Cart cartId;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @NotNull
    @Column(name = "DATE_CREATED")
    private LocalDate created;
}
