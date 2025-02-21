package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
    public ArrayList<Product> products;
    private final int maxAmountOfProducts = 10;

    public VendingMachine() {
        products = new ArrayList<>();
    }

    public void fillEmptyMachine(){
        for(int i = 0; maxAmountOfProducts > i; i++){
            products.add(new Product(Type.COLA));
            products.add(new Product(Type.SPRITE));
            products.add(new Product(Type.SNICKERS));
            products.add(new Product(Type.SANDWICH));
        }
    }
    public void buyProduct(Type type){
        for (Product product : products){
            if(product.getProductSort() == type){
                products.remove(product);
                break;
            }
        }
        fillProductStock(type);
    }

    public void fillProductStock(Type type) {
        int amountOfProductOfThisSort = 0;
        for (Product product : products){
            if(product.getProductSort() == type){
                amountOfProductOfThisSort++;
                break;
            }
        }
        if(amountOfProductOfThisSort < maxAmountOfProducts * 0.3){
            for (int i = amountOfProductOfThisSort; maxAmountOfProducts > amountOfProductOfThisSort;
                 amountOfProductOfThisSort++){
                products.add(new Product(type));
            }
        }
    }

}
