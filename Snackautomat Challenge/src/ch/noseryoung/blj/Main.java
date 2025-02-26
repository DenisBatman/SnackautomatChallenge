package ch.noseryoung.blj;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Vending Machine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        UI userInterface = new UI();
        window.add(userInterface);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        userInterface.launchGame();
    }
}


