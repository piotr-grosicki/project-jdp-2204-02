package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "DATE_CREATED")
    private LocalDate created = LocalDate.now();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Product> products = new ArrayList<>();

    @NotNull
    @Column(name="ORDER_CANCELED")
    private Boolean orderCanceled=Boolean.FALSE;

}
