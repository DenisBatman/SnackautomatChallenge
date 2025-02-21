package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Menu {
    ArrayList<Customer> customers = new ArrayList<>();
    VendingMachine vendingMachine;
    Scanner input = new Scanner(System.in);

    public Menu(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    public void startMenu(){

        System.out.println("Are you a customer(0) or an employee(other numba)?");
        if(input.nextInt() == 0){
            System.out.println("Enter your Name: ");
            String name = input.nextLine();
            if(!isAlreadyCustomer(name)){
                customers.add(new Customer(name));
            }
            for(Customer customer : customers){
                if(Objects.equals(customer.getName(), name)){
                    customerMenu(customer);
                }
            }
        } else {
            //ask for password 
        }
    }


    public void customerMenu(Customer customer){
        Scanner input = new Scanner(System.in);
        System.out.println("What product do you want to buy?");
        for(ProductSort productSort : vendingMachine.productSorts){
            System.out.println(productSort.getName());
        }
        String productName = input.nextLine();
        for (ProductSort productSort : vendingMachine.productSorts){
            if(Objects.equals(productSort.getName(), productName)){

            }
        }

    }
    public boolean isAlreadyCustomer(String name) {
        for(Customer customer : customers){
            if(Objects.equals(customer.getName(), name)){
                return true;
            }
        }
        return false;
    }
}
