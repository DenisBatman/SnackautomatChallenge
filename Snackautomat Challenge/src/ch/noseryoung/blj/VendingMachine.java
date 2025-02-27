package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Objects;

public class VendingMachine {
    public ArrayList<ProductSort> productSorts;
    boolean isInPayment = false;
    ProductSort currentProduct;

    public VendingMachine() {
        productSorts = new ArrayList<>();
    }

    public void fillEmptyMachine(){
        productSorts.add(new ProductSort("Chips", 3.5));
        productSorts.add(new ProductSort("Fanta", 1.6));
        productSorts.add(new ProductSort("M&Ms", 2.5));
        productSorts.add(new ProductSort("Mars", 2.5));
        productSorts.add(new ProductSort("Prime", 3.5));
        productSorts.add(new ProductSort("Twix", 2.5));
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
                    isInPayment(productName, customer, amount * productSort.getPrice(), amount);
                } else {
                    System.out.println("Not enough credits");
                }
                break;
            }
        }

    }

    public void isInPayment(String productName, Customer customer, double price, int amount)
    {
        isInPayment = true;
        double oldCredit = customer.getCredit();
        double paidCredit = 0;
        double needToPay = price;
        do {
            Coin coin = new PayCoin().paymentInterface(oldCredit, paidCredit, needToPay);
            switch (coin) {
                case FIVE_CENTS:
                    customer.setCredit((double) Math.round((customer.getCredit() - 0.05) * 100) /100);
                    needToPay -= 0.05;
                    paidCredit += 0.05;
                    break;
                case TEN_CENTS:
                    if (customer.getCredit() >= 0.1) {
                        customer.setCredit((double) Math.round((customer.getCredit() - 0.1) * 100) / 100);
                        needToPay -= 0.1;
                        paidCredit += 0.1;
                    }
                    break;
                case TWENTY_CENTS:
                    if (customer.getCredit() >= 0.2) {
                        customer.setCredit((double) Math.round((customer.getCredit() - 0.2) * 100) / 100);
                        needToPay -= 0.2;
                        paidCredit += 0.2;
                    }
                    break;
                case FIFTY_CENTS:
                    if (customer.getCredit() >= 0.5) {
                        customer.setCredit((double) Math.round((customer.getCredit() - 0.5) * 100) / 100);
                        needToPay -= 0.5;
                        paidCredit += 0.5;
                    }
                    break;
                case ONE_DOLLAR:
                    if (customer.getCredit() >= 1) {
                        customer.setCredit((double) Math.round((customer.getCredit() - 1) * 100) / 100);
                        needToPay -= 1;
                        paidCredit += 1;
                    }
                    break;
                case TWO_DOLLARS:
                    if (customer.getCredit() >= 2) {
                        customer.setCredit((double) Math.round((customer.getCredit() - 2) * 100) / 100);
                        needToPay -= 2;
                        paidCredit += 2;
                    }
                    break;
                case FIVE_DOLLARS:
                    if (customer.getCredit() >= 5) {
                        customer.setCredit((double) Math.round((customer.getCredit() - 5) * 100) / 100);
                        needToPay -= 5;
                        paidCredit += 5;
                    }
                    break;
                case CANCEL:
                    customer.setCredit(oldCredit);
                    isInPayment = false;
                    break;
                case null:
                    break;
                default:
                    System.out.println("No such Coin");
                    break;
            }
            if(paidCredit >= price){
                isInPayment = false;
            }
        } while (isInPayment);
        String confirm = new JFrameUserInputField("Confirm purchase? (y/n)").getString().trim().toLowerCase();
        if(!confirm.equals("y") && !confirm.equals("yes")) {
            customer.setCredit(oldCredit);
        } else {
            customer.setCredit(oldCredit - price);
            for(int i = 0; amount > i; i++){
                buyProduct(productName);
            }
        }
    }

}

