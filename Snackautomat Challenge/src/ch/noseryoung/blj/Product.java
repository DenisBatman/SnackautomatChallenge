package ch.noseryoung.blj;

import java.util.UUID;

public class Product {
    private final UUID productId;
    private final Sort productsort;

    public Product(Sort productsort) {
        this.productsort = productsort;
        productId = UUID.randomUUID();
    }



    public UUID getProductId() {
        return productId;
    }

    public Sort getProductsort() {
        return productsort;
    }
}
