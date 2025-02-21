package ch.noseryoung.blj;

import java.util.Objects;
import java.util.Scanner;

public class Maintenance {
    VendingMachine vendingMachine;
    public void maintenanceMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Vending Machine Admin Interface!");
        System.out.println("1. New product");
        System.out.println("2. Fill empty machine");
        System.out.println("3. Change price");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1 -> // not yet added;
            case 2 -> vendingMachine.fillEmptyMachine();
            case 3 -> // not yet added;
            default -> System.out.println("Invalid choice");
        }

    }
    private void addProduct(){
        Scanner input = new Scanner(System.in);
        System.out.println("What should be the new products name?");
        String productName = input.nextLine();
        System.out.printf("What should be the price of %s?", productName);
        double price = input.nextDouble();
        vendingMachine.productSorts.add(new ProductSort(productName, price));
    }
    private void changePrice(){
        boolean priceChanged = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Which products price do you want to change?");
        String productName = input.nextLine();
        System.out.printf("What should be the new price of %s?", productName);
        double price = input.nextDouble();

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
}
