package ch.noseryoung.blj;

import java.util.UUID;

public class Product {
    private final UUID productId;
    private final String productSort;

    public Product(String productSort) {
        this.productSort = productSort;
        productId = UUID.randomUUID();
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductSort() {
        return productSort;
    }

}
