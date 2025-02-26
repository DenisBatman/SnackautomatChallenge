package ch.noseryoung.blj;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Maintenance {
    Scanner input = new Scanner(System.in);
    VendingMachine vendingMachine;

    public Maintenance(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    public void maintenanceMenu(){
        System.out.println("Welcome to the Vending Machine Admin Interface!");
        System.out.println("1. New product");
        System.out.println("2. Fill empty machine");
        System.out.println("3. Change price");
        System.out.println("4. Exchange product");
        System.out.println("5. Delete product");
        System.out.println("6. Change credit of customer");
        System.out.print("Your choice: ");
        int choice = input.nextInt();
        input.nextLine();
        switch(choice){
            case 1 -> addProduct();
            case 2 -> vendingMachine.fillEmptyMachine();
            case 3 -> changePrice();
            case 4 -> exchangeProduct();
            case 5 -> deleteProduct();
            case 6 -> changeCustomerCredit();
            default -> System.out.println("Invalid choice");
        }

    }
    private void addProduct(){
        System.out.println("What should be the new products name?");
        String productName = input.nextLine();
        double price = getPriceFromUser(productName);
        vendingMachine.productSorts.add(new ProductSort(productName, price));
    }
    private void changePrice(){
        boolean priceChanged = false;
        System.out.println("Which products price do you want to change?");
        String productName = input.nextLine();
        double price = getPriceFromUser(productName);

        for(ProductSort productSort : vendingMachine.productSorts){
            if(Objects.equals(productSort.getName(), productName)){
                productSort.setPrice(price);
                System.out.printf("The new Price is %.2f", price);
                priceChanged = true;
            }
        }
        if(!priceChanged){
            System.out.println("There is no such product");
        }
    }
    private void exchangeProduct(){
        boolean productExchanged = false;
        System.out.println("Which existing product do you want to exchange?");
        String oldProductName = input.nextLine();
        for(ProductSort productSort : vendingMachine.productSorts){
            if(Objects.equals(productSort.getName(), oldProductName)){
                System.out.println("What should be the new products name?");
                String newProductName = input.nextLine();
                double newPrice = getPriceFromUser(newProductName);
                vendingMachine.productSorts.remove(productSort);
                vendingMachine.productSorts.add(new ProductSort(newProductName, newPrice));
                productExchanged = true;
                break;
            }
        }
        if(!productExchanged){
            System.out.println("No such product.");
        }
    }
    private void deleteProduct(){
        boolean productDeleted = false;
        System.out.println("Which product do you want to delete?");
        String productName = input.nextLine();
        for(ProductSort productSort : vendingMachine.productSorts) {
            if (Objects.equals(productSort.getName(), productName)) {
                productDeleted = true;
                vendingMachine.productSorts.remove(productSort);
                break;
            }
        }
        if(!productDeleted){
            System.out.println("There is no such product.");
        }

    }
    private double getPriceFromUser(String productName){
        System.out.printf("What should be the price of %s?", productName);
        return  input.nextDouble();
    }
    private void changeCustomerCredit(){
        boolean changedCustomerCredit = false;
        System.out.println("Which customer's credit should be edited");
        String customerName = input.nextLine();
        for(Customer customer : Menu.customers) {
            if (Objects.equals(customer.getName(), customerName)) {
                System.out.println("What should be the new credit?");
                double credit = input.nextDouble();
                if(credit > 0){
                    customer.setCredit(credit);
                    changedCustomerCredit = true;
                }
            }
        }
        if(!changedCustomerCredit){
            System.out.println("Invalid input!");
        }
    }
}
