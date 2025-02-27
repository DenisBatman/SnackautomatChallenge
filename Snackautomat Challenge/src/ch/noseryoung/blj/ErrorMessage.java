package ch.noseryoung.blj;

import javax.swing.*;

public class ErrorMessage extends JFrame {
    public ErrorMessage (String text){
        JFrame error = new JFrame();
        JOptionPane.showMessageDialog(error,
                text,
                "ERROR",
                JOptionPane.WARNING_MESSAGE);
    }
}
