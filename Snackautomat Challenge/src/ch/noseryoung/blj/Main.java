package ch.noseryoung.blj;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose interface:");
        System.out.println("1. Graphical User Interface (GUI)");
        System.out.println("2. Command Line Interface (CLI)");
        System.out.print("Enter your choice (1 or 2): ");

        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to GUI.");
            choice = 1;
        }

        if (choice == 1) {

            launchGUI();
        } else if (choice == 2) {
            launchCLI();
        } else {
            System.out.println("Invalid choice. Defaulting to GUI.");

            launchGUI();
        }
    }

    public static void launchGUI() {
        launchGUI(null);
    }
    
    public static void launchGUI(VendingMachine existingMachine) {
        JFrame window = new JFrame("Vending Machine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        VendingMachine vendingMachine;
        if (existingMachine == null) {
            vendingMachine = new VendingMachine();
            vendingMachine.fillEmptyMachine();
        } else {
            vendingMachine = existingMachine;
        }

        UI userInterface = new UI(vendingMachine);
        window.add(userInterface);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        userInterface.launchApplication();
    }

    public static void launchCLI() {
        launchCLI(null);
    }
    
    public static void launchCLI(VendingMachine existingMachine) {
        try {
            VendingMachine vendingMachine;
            if (existingMachine == null) {
                vendingMachine = new VendingMachine();
                vendingMachine.fillEmptyMachine();
            } else {
                vendingMachine = existingMachine;
            }

            Menu menu = new Menu(vendingMachine);
            menu.startMenu();

            System.out.println("\nThank you for using the vending machine. Goodbye!");
        } catch (Exception e) {
            System.err.println("Error launching CLI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}