package com.example.productapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Product")
public class Product {

    @Column(name = "product_id")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String product_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_price")
    private double product_price;


    public Product(){}

    public String getProduct_id() {
        if (product_id.isEmpty())
            this.setProduct_id();
        return product_id;
    }

    public void setProduct_id() {
        this.product_id = UUID.randomUUID().toString();;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
