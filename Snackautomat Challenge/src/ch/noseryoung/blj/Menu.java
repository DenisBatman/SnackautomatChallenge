package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import javax.smartcardio.*;
import java.util.List;

public class Menu {
    ArrayList<Customer> customers = new ArrayList<>();
    Scanner input = new Scanner(System.in);

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
            if(authentification()){
                Maintence maintence = new Maintence();
                maintence.maintence();
            }
        }
    }
    boolean authentification(){
        System.out.println("Put your NFC tag to the reader");
        String name = input.nextLine();
        boolean authentification = false;
        if(authentification){
            System.out.println("Thanks");
            return true;
        }else{
            System.out.println("You dont have Admin permissions.");
            System.out.println("if you beleave is a mistake contact Admin(admin@snack.ch)");
            return false;
        }

    }

    public void customerMenu(Customer customer){
        System.out.println("What product do you want to buy?");
        // display available products
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
