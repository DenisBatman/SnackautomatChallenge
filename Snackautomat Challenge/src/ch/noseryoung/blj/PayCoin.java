package ch.noseryoung.blj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayCoin extends JFrame {
    private JButton FIVE_CENTS;
    private JButton TEN_CENTS;
    private JButton TWENTY_CENTS;
    private JButton FIFTY_CENTS;
    private JButton ONE_DOLLAR;
    private JButton TWO_DOLLARS;
    private JButton FIVE_DOLLARS;
    private JButton CANCEL;
    private JTextField oldCredit;
    private JTextField alreadyPaid;
    private JTextField currentCredit;
    private JTextField needToPay;

    public PayCoin() {
        setTitle("Payment");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());
        FIVE_CENTS = new JButton("-.05");
        TEN_CENTS = new JButton("-.10");
        TWENTY_CENTS = new JButton("-.20");
        FIFTY_CENTS = new JButton("-.50");
        ONE_DOLLAR = new JButton("1.-");
        TWO_DOLLARS = new JButton("2.-");
        FIVE_DOLLARS = new JButton("5.-");
        CANCEL = new JButton("Cancel");
    }

    public Coin paymentInterface(double oldCredit, double paidCredit, double needToPay){
        this.oldCredit = new JTextField("Your old credit: " + oldCredit);
        this.alreadyPaid = new JTextField("You already paid: " + paidCredit);
        this.currentCredit = new JTextField("You current credit: " + (oldCredit - paidCredit));
        this.needToPay = new JTextField("You need to pay: " + needToPay);

        this.oldCredit.setEditable(false);
        this.oldCredit.setSize(500, 10);

        this.alreadyPaid.setEditable(false);
        this.oldCredit.setSize(500, 10);

        this.currentCredit.setEditable(false);
        this.oldCredit.setSize(500, 10);

        this.needToPay.setEditable(false);
        this.oldCredit.setSize(500, 10);


        FIVE_CENTS.setSize(150, 150);
        TEN_CENTS.setSize(150, 150);
        TWENTY_CENTS.setSize(150, 150);
        ONE_DOLLAR.setSize(150, 150);
        TWO_DOLLARS.setSize(150, 150);
        FIVE_DOLLARS.setSize(150, 150);
        CANCEL.setSize(150, 150);

        add(this.oldCredit);
        add(this.alreadyPaid);
        add(this.currentCredit);
        add(this.needToPay);
        add(FIVE_CENTS);
        add(TEN_CENTS);
        add(TWENTY_CENTS);
        add(FIFTY_CENTS);
        add(ONE_DOLLAR);
        add(TWO_DOLLARS);
        add(FIVE_DOLLARS);
        add(CANCEL);

        setLocationRelativeTo(null);
        setVisible(true);
        while(true){
            final Coin[] coin = {null};
            FIVE_CENTS.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.FIVE_CENTS;
                }
            });
            TEN_CENTS.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.TEN_CENTS;
                }
            });
            TWENTY_CENTS.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.TWENTY_CENTS;
                }
            });
            FIFTY_CENTS.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.FIFTY_CENTS;
                }
            });
            ONE_DOLLAR.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.ONE_DOLLAR;
                }
            });
            TWO_DOLLARS.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.TWO_DOLLARS;
                }
            });
            FIVE_DOLLARS.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.FIVE_DOLLARS;
                }
            });
            CANCEL.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    coin[0] = Coin.CANCEL;
                }
            });

            if(coin[0] != null){
                setVisible(false);
                return coin[0];
            }
        }
    }

    private boolean isCoinPushed(JButton button){
        final boolean[] isPushed = {false};
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event) {
                    isPushed[0] = true;
                }
            });
        return isPushed[0];
    }

}
