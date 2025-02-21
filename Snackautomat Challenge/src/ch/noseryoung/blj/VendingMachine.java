package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Objects;

public class VendingMachine {
    public ArrayList<ProductSort> productSorts;
    private final int maxAmountOfProducts = 10;

    public VendingMachine() {
        productSorts = new ArrayList<ProductSort>();
    }

    public void fillEmptyMachine(){
        productSorts.add(new ProductSort("COLA", 1.5));
        productSorts.add(new ProductSort("SPRITE", 1.6));
        productSorts.add(new ProductSort("SNICKERS", 2.5));
        productSorts.add(new ProductSort("SANDWICH", 7.5));
    }

    public void buyProduct(String productName){
        for (ProductSort productSort : productSorts){
            if(Objects.equals(productSort.getName(), productName)){
                productSort.products.removeFirst();
                productSort.fillStock();
                break;
            }
        }
    }
    public void startTransaction(String productName, int amount, Customer customer){
        for (ProductSort productSort : productSorts){
            if(Objects.equals(productSort.getName(), productName)){
                if(customer.getCredit() >= productSort.getPrice() * amount && productSort.products.size() >= amount){
                    for(int i = 0; amount > i; i++){
                        buyProduct(productName);
                    }
                }
                else {
                    System.out.println("Nicht genügend Credits");
                }
                break;
            }
        }

    }


}

