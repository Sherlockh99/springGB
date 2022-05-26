package org.sherlock.products;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Product {
    @Setter
    private Long id;

    private final String title;

    @Setter
    private double cost;

    private final String productName;

    public Product(String productName, String title, double cost ){
        this.productName = productName;
        this.title = title;
        this.cost = cost;
    }
}
