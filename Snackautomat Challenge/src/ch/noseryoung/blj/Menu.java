package ch.noseryoung.blj;

import javax.smartcardio.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javax.smartcardio.*;

public class Menu {

    static ArrayList<Customer> customers = new ArrayList<>();
    VendingMachine vendingMachine;
    Scanner input = new Scanner(System.in);

    public Menu(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void startMenu(){
        System.out.println("Are you a customer(0) or an employee(other numba)?");
        if(input.nextInt() == 0){
            input.nextLine(); // Clear buffer
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
                Maintenance maintenance = new Maintenance(vendingMachine);
                maintenance.maintenanceMenu();
            }
        }
    }

    boolean authentification(){
        System.out.println("Put your NFC tag on the reader...");

        try {
            // Get the terminal factory
            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            if (terminals.isEmpty()) {
                System.out.println("No NFC reader found.");
                return false;
            }

            // Look specifically for ACS ACR122U reader
            CardTerminal terminal = null;
            for (CardTerminal t : terminals) {
                if (t.getName().contains("ACS ACR122U")) {
                    terminal = t;
                    break;
                }
            }

            if (terminal == null) {
                System.out.println("ACS ACR122U reader not found.");
                return false;
            }

            System.out.println("Using NFC Reader: " + terminal.getName());

            // Wait for a card (30 seconds)
            System.out.println("Waiting for card...");
            if (!terminal.waitForCardPresent(30000)) {
                System.out.println("No card detected within the timeout period.");
                return false;
            }

            // Connect to the card
            Card card = terminal.connect("T=1");
            CardChannel channel = card.getBasicChannel();

            // Get ATR and print it
            ATR atr = card.getATR();
            byte[] atrBytes = atr.getBytes();
            System.out.println("Card ATR: " + bytesToHex(atrBytes));

            // Command for getting UID: FF CA 00 00 00
            byte[] getUidCommand = new byte[]{(byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00};
            ResponseAPDU response = channel.transmit(new CommandAPDU(getUidCommand));

            // Check if command was successful (SW1SW2 = 9000)
            if (response.getSW() != 0x9000) {
                System.out.println("Failed to read card UID. Error code: " + Integer.toHexString(response.getSW()));
                return false;
            }

            byte[] uid = response.getData();
            String uidString = bytesToHex(uid);

            System.out.println("Detected Card UID: " + uidString);

            // Disconnect from the card
            card.disconnect(false);

            if (isAdminCard(uidString)) {
                System.out.println("Authentication successful. Access granted.");
                return true;
            } else {
                System.out.println("You don't have admin permissions.");
                System.out.println("If you believe this is a mistake, contact Admin (admin@snack.ch)");
                return false;
            }
        } catch (CardException e) {
            System.out.println("Card Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdminCard(String uid) {
        // List of admin NFC card UIDs - merged from both branches
        List<String> adminUIDs = List.of(
                "04 5F 88 1A 6D 74 80", // Nepomuk
                "04 1F 23 4A 01 4F 80", // Denis
                "04 26 8B AA B6 57 80" // Michel
        );

        System.out.println("Checking card UID against admin list:");
        for (String adminUid : adminUIDs) {
            System.out.println("- hidden -");
        }

        return adminUIDs.contains(uid);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X ", b));
        }
        return hexString.toString().trim();
    }

    public void customerMenu(Customer customer){
        Scanner input = new Scanner(System.in);
        boolean continueShoppingFlag = true;

        while(continueShoppingFlag) {
            System.out.println("\n----- CUSTOMER MENU -----");
            System.out.println("Your current credit: $" + String.format("%.2f", customer.getCredit()));
            System.out.println("\nAvailable products:");

            if(vendingMachine.productSorts.isEmpty()) {
                System.out.println("No products available. Please contact an employee.");
                return;
            }

            // Display products
            int productCounter = 1;
            for(ProductSort productSort : vendingMachine.productSorts){
                System.out.printf("%d. %s - $%.2f (Available: %d)\n",
                        productCounter++,
                        productSort.getName(),
                        productSort.getPrice(),
                        productSort.products.size());
            }

            System.out.println("\nSelect a product by number (or 0 to exit): ");
            int productChoice;
            try {
                productChoice = input.nextInt();
                input.nextLine(); // Clear buffer
            } catch(Exception e) {
                System.out.println("Invalid selection. Please enter a number.");
                input.nextLine(); // Clear buffer
                continue;
            }

            // Exit
            if(productChoice == 0) {
                continueShoppingFlag = false;
                continue;
            }

            // Validate
            if(productChoice < 1 || productChoice > vendingMachine.productSorts.size()) {
                System.out.println("Invalid product number. Please try again.");
                continue;
            }

            // Get the selected product
            ProductSort selectedProduct = vendingMachine.productSorts.get(productChoice - 1);

            //if product is available
            if(selectedProduct.products.isEmpty()) {
                System.out.println("Sorry, " + selectedProduct.getName() + " is out of stock.");
                continue;
            }

            //quantity
            System.out.println("How many " + selectedProduct.getName() + " would you like to purchase?");
            int quantity;
            try {
                quantity = input.nextInt();
                input.nextLine(); // Clear buffer
            } catch(Exception e) {
                System.out.println("Invalid quantity. Please enter a number.");
                input.nextLine(); // Clear buffer
                continue;
            }

            // Validate
            if(quantity <= 0) {
                System.out.println("Please enter a positive quantity.");
                continue;
            }

            // Check stock
            if(quantity > selectedProduct.products.size()) {
                System.out.println("Sorry, only " + selectedProduct.products.size() +
                        " units available. Please select a smaller quantity.");
                continue;
            }

            // Calculate total price
            double totalPrice = selectedProduct.getPrice() * quantity;

            // customer has enough credit
            if(customer.getCredit() < totalPrice) {
                System.out.println("Insufficient credit. You need $" +
                        String.format("%.2f", totalPrice) + " but you only have $" +
                        String.format("%.2f", customer.getCredit()));

                System.out.println("Would you like to add payment? (y/n)");
                String addPayment = input.nextLine().trim().toLowerCase();

                if(addPayment.equals("y") || addPayment.equals("yes")) {
                    vendingMachine.isInPayment(customer, totalPrice);
                }
                continue;
            }

            // Process transaction
            System.out.println("Purchasing " + quantity + " " + selectedProduct.getName() +
                    " for $" + String.format("%.2f", totalPrice));

            // Confirm purchase
            System.out.println("Confirm purchase? (y/n)");
            String confirm = input.nextLine().trim().toLowerCase();

            if(confirm.equals("y") || confirm.equals("yes")) {
                // Update customer credit
                customer.setCredit(customer.getCredit() - totalPrice);

                // Process the purchase
                vendingMachine.startTransaction(selectedProduct.getName(), quantity, customer);

                System.out.println("Thank you for your purchase!");
                System.out.println("Your remaining credit: $" + String.format("%.2f", customer.getCredit()));

                // Ask if customer wants to continue shopping
                System.out.println("Would you like to make another purchase? (y/n)");
                String continueShopping = input.nextLine().trim().toLowerCase();

                if(!continueShopping.equals("y") && !continueShopping.equals("yes")) {
                    continueShoppingFlag = false;
                }
            }
        }

        System.out.println("Thank you for using our vending machine!");
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