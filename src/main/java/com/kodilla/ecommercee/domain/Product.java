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
    @Column(name = "PRODUCT_ID")
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cartProducts",
                 joinColumns = @JoinColumn(name = "PRODUCTS_ID"),
                 inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<Cart> carts;

    public Product(Group group, String productName, BigDecimal productPrice, String productDescription) {
        this.group = group;
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

