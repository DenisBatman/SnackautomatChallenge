package ch.noseryoung.blj;

import java.util.UUID;

public class Product {
    UUID productId;
    private final Sort productsort;
    public Product (Sort productsort){
        this.productsort = productsort;
        productId = UUID.randomUUID();
    }

}
