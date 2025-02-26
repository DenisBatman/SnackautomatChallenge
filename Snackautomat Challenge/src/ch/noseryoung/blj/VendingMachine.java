package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Objects;

public class VendingMachine {
    public ArrayList<ProductSort> productSorts;
    private final int maxAmountOfProducts = 10;
    int vendingMachineCredits;

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



    public void isInPayment(Customer customer, double price)
    {
        boolean isInPayment = true;
        double oldCredit = customer.getCredit();
        double paidCredit = 0;
        double needToPay = price;
        do {
            System.out.println("You need ");
            Coin coin = getCoin();
            switch (coin) {
                case FIVE_CENTS:
                    customer.setCredit(customer.getCredit() - 0.05);
                    needToPay -= 0.05;
                    paidCredit += 0.05;
                    break;
                case TEN_CENTS:
                    customer.setCredit(customer.getCredit() - 0.1);
                    needToPay -= 0.1;
                    paidCredit += 0.1;
                    break;
                case TWENTY_CENTS:
                    customer.setCredit(customer.getCredit() - 0.2);
                    needToPay -= 0.2;
                    paidCredit += 0.2;
                    break;
                case FIFTY_CENTS:
                    customer.setCredit(customer.getCredit() - 0.5);
                    needToPay -= 0.5;
                    paidCredit += 0.5;
                    break;
                case DOLLAR:
                    customer.setCredit(customer.getCredit() - 1);
                    needToPay -= 1;
                    paidCredit += 1;
                    break;
                case TWO_DOLLARS:
                    customer.setCredit(customer.getCredit() - 2);
                    needToPay -= 2;
                    paidCredit += 2;
                    break;
                case FIVE_DOLLARS:
                    customer.setCredit(customer.getCredit() - 5);
                    needToPay -= 5;
                    paidCredit += 5;
                    break;
                case CANCEL:
                    customer.setCredit(oldCredit);
                    isInPayment = false;
                    break;
                default:
                    System.out.println("No such Coin");
                    break;
            }
            if(paidCredit >= price){
                isInPayment = false;
            }
        } while (isInPayment);
    }

    //temporary later need to be with ui
    public Coin getCoin(){
        return Coin.DOLLAR;
    }

}

