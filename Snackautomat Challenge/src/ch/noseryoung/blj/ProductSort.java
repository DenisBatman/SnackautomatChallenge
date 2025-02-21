package ch.noseryoung.blj;

import java.util.ArrayList;

public class ProductSort {
    int MAX_AMOUNT_OF_PRODUCTS = 10;
    public ArrayList<Product> products;
    private final String name;
    private int numberOfProducts;
    private final double price;
    public ProductSort(String name, double price){
        numberOfProducts = 0;
        this.name = name;
        for(int i = 0; MAX_AMOUNT_OF_PRODUCTS > i; i++){
            products.add(new Product(name));
            numberOfProducts++;
        }
        this.price = price;
    }

    public void fillStock(){
        if(products.size() < MAX_AMOUNT_OF_PRODUCTS * 0.3){
            for(int i = products.size(); MAX_AMOUNT_OF_PRODUCTS >= i; i++){
                products.add(new Product(name));
            }
        }
    }
    public int getNumberOfProducts(){return numberOfProducts;}
    public void setNumberOfProducts(int numberOfProducts){this.numberOfProducts = numberOfProducts;}
    public String getName(){return name;}
    public double getPrice() {return price;}



}
