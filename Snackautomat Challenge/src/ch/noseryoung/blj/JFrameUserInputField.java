package ch.noseryoung.blj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameUserInputField extends JFrame {
    private JTextField inputField;
    private JButton validateButton;
    private int number;
    private String text;

    public JFrameUserInputField(String title){
        setTitle(title);
        setSize(500, 100);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());
        inputField = new JTextField(15);
        validateButton = new JButton("Enter");
    }

    public int getInteger() {
        number = -1;
        add(inputField);
        add(validateButton);

        setLocationRelativeTo(null);
        setVisible(true);
        while (number == -1){
            validateButton.addActionListener(new ActionListener (){
                @Override
                public void actionPerformed(ActionEvent event) {
                    setNumber(Integer.parseInt(inputField.getText()));
                    setVisible(false);
                }
            });
        }
        return number;
    }
    public String getString() {
        text = null;
        add(inputField);
        add(validateButton);
        setLocationRelativeTo(null);
        setVisible(true);
        while(text == null){
            validateButton.addActionListener(new ActionListener (){
                @Override
                public void actionPerformed(ActionEvent event) {
                    if(text != null){
                        setText(inputField.getText());
                        setVisible(false);
                    }
                }
            });
        }
        return text;
    }
    private void setNumber (int number){this.number = number;}
    private void setText (String text){this.text = text;}
}
