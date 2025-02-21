package ch.noseryoung.blj;

import java.util.UUID;

public class Product {
    private final UUID productId;
    private final Type productSort;
    private final double price;

    public Product(Type productSort) {
        this.productSort = productSort;
        productId = UUID.randomUUID();
        switch(productSort) {
            case COLA -> price = 1.5;
            case SPRITE -> price = 1.6;
            case SNICKERS -> price = 2.5;
            default -> price = 7.5;
        }
    }

    public UUID getProductId() {
        return productId;
    }

    public Type getProductSort() {
        return productSort;
    }

    public double getPrice() {return price;}
}
