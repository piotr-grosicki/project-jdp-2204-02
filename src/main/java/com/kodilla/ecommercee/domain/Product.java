package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @NotNull
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal productPrice;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String productDescription;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "cartProducts",
               joinColumns = @JoinColumn(name = "cart_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Cart> carts;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "orderProducts",
            joinColumns = @JoinColumn(name = "PRODUCTS_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
    private List<Order> orders;

    public Product(Group group, String productName, BigDecimal productPrice, String productDescription) {
        this.group = group;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public Product(Long productId, String productName, BigDecimal productPrice, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public Product(String productName, BigDecimal productPrice, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }
}

