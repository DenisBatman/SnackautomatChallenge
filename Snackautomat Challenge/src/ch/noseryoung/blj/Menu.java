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

        List<String> adminUIDs = List.of(
                "04 A1 B2 C3 D4",
                "AB CD EF 12 34",
                "3B 81 80 01 80 80" // Nepomuk
        );

        System.out.println("Checking card UID against admin list:");
        for (String adminUid : adminUIDs) {
            System.out.println("- " + adminUid);
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
        System.out.println("What product do you want to buy?");
        for(ProductSort productSort : vendingMachine.productSorts){
            System.out.println(productSort.getName());
        }
        String productName = input.nextLine();
        for (ProductSort productSort : vendingMachine.productSorts){
            if(Objects.equals(productSort.getName(), productName)){
                // Product selection customer
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