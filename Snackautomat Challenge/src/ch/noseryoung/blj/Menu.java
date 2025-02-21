package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Menu {
    ArrayList<Customer> customers = new ArrayList<>();

    public void startMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Are you a customer(0) or an employee(other nu)?");
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
            //ask for password (security
        }
    }


    public void customerMenu(Customer customer){
        
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
