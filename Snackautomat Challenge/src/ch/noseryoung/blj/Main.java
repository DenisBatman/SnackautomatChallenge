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
            // Start GUI - close scanner
            scanner.close();
            launchGUI();
        } else if (choice == 2) {
            launchCLI();
        } else {
            System.out.println("Invalid choice. Defaulting to GUI.");
            scanner.close();
            launchGUI();
        }
    }

    private static void launchGUI() {
        JFrame window = new JFrame("Vending Machine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        VendingMachine vendingMachine = new VendingMachine();

        UI userInterface = new UI(vendingMachine);
        window.add(userInterface);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        userInterface.launchApplication();
    }

    private static void launchCLI() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.fillEmptyMachine();

        Menu menu = new Menu(vendingMachine);
        menu.startMenu();

        System.out.println("\nThank you for using the vending machine. Goodbye!");
    }
}