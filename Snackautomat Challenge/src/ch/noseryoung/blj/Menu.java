package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javax.smartcardio.*;

public class Menu {
    ArrayList<Customer> customers = new ArrayList<>();
    Scanner input = new Scanner(System.in);

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
                Maintence maintence = new Maintence();
                maintence.maintence();
            }
        }
    }

    boolean authentification(){
        System.out.println("Put your NFC tag on the reader...");

        try {
            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();

            if (terminals.isEmpty()) {
                System.out.println("No NFC reader found.");
                return false;
            }

            CardTerminal terminal = terminals.get(0);
            System.out.println("Using NFC Reader: " + terminal.getName());

            // wait for nfc tag
            terminal.waitForCardPresent(0);

            // Connect to the NFC tag
            Card card = terminal.connect("*");
            CardChannel channel = card.getBasicChannel();

            // get the card UID
            byte[] commandUID = new byte[]{(byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00};
            CommandAPDU command = new CommandAPDU(commandUID);
            ResponseAPDU response = channel.transmit(command);

            byte[] uid = response.getData();
            String uidString = bytesToHex(uid);

            System.out.println("Detected Card UID: " + uidString);


            if (isAdminCard(uidString)) {
                System.out.println("Authentication successful. Access granted.");
                return true;
            } else {
                System.out.println("You don't have admin permissions.");
                System.out.println("If you believe this is a mistake, contact Admin (admin@snack.ch)");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdminCard(String uid) {
<<<<<<< Updated upstream
        // List of admin NFC card UIDs
        List<String> adminUIDs = List.of("04 A1 B2 C3 D4", "AB CD EF 12 34");
=======

        List<String> adminUIDs = List.of(
                "04 5F 88 1A 6D 74 80", // Nepomuk
                "04 1F 23 4A 01 4F 80", // Denis
                "04 26 8B AA B6 57 80" // Michel

        );

        System.out.println("Checking card UID against admin list:");
        for (String adminUid : adminUIDs) {
            System.out.println("- " + adminUid);
        }

>>>>>>> Stashed changes
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
        System.out.println("What product do you want to buy?");
        // Display available products
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
