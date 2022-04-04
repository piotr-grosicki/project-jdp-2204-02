package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long productId;

    //TODO: odkomentowac po powstaniu encji ProductGroup
   // @OneToMany(mappedBy = "id")
    //private Set<ProductGroup> productGroupId;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @NotNull
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal productPrice;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String productDescription;

    //TODO: odkomentowac po powstaniu encji Cart
   // @ManyToMany(cascade = CascadeType.MERGE)
    //@JoinTable(name = "cartProducts",
      //           joinColumns = @JoinColumn(name = "PRODUCTS_ID"),
        //         inverseJoinColumns = @JoinColumn(name = "cart_id"))
    //private List<Cart> carts;

}

