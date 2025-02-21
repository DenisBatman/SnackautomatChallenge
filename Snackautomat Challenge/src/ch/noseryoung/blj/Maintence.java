package ch.noseryoung.blj;

import java.util.Scanner;

public class Maintence {
    VendingMachine vendingMachine;
    public void maintence(){
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
    private void addProduct(String productName, Type productSort){
        vendingMachine.products.add();
    }
}
