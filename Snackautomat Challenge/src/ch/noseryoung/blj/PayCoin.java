package ch.noseryoung.blj;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class PayCoin extends JFrame {
    private final JButton FIVE_CENTS;
    private final JButton TEN_CENTS;
    private final JButton TWENTY_CENTS;
    private final JButton FIFTY_CENTS;
    private final JButton ONE_DOLLAR;
    private final JButton TWO_DOLLARS;
    private final JButton FIVE_DOLLARS;
    private final JButton CANCEL;

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
        JTextField oldCreditText = new JTextField("Your old credit: " + oldCredit);
        JTextField alreadyPaid = new JTextField("You already paid: " + (double)Math.round(paidCredit*100)/100);
        JTextField currentCredit = new JTextField("You current credit: " + (double)Math.round((oldCredit - paidCredit)*100)/100);
        JTextField needToPayText = new JTextField("You need to pay: " + (double)Math.round(needToPay*100)/100);

        oldCreditText.setEditable(false);
        oldCreditText.setSize(500, 10);

        alreadyPaid.setEditable(false);
        oldCreditText.setSize(500, 10);

        currentCredit.setEditable(false);
        oldCreditText.setSize(500, 10);

        needToPayText.setEditable(false);
        oldCreditText.setSize(500, 10);


        FIVE_CENTS.setSize(150, 150);
        TEN_CENTS.setSize(150, 150);
        TWENTY_CENTS.setSize(150, 150);
        ONE_DOLLAR.setSize(150, 150);
        TWO_DOLLARS.setSize(150, 150);
        FIVE_DOLLARS.setSize(150, 150);
        CANCEL.setSize(150, 150);

        add(oldCreditText);
        add(alreadyPaid);
        add(currentCredit);
        add(needToPayText);
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
            AtomicReference<Coin> coin = new AtomicReference<>();
            FIVE_CENTS.addActionListener(_ -> coin.set(Coin.FIVE_CENTS));
            TEN_CENTS.addActionListener(_ -> coin.set(Coin.TEN_CENTS));
            TWENTY_CENTS.addActionListener(_ -> coin.set(Coin.TWENTY_CENTS));
            FIFTY_CENTS.addActionListener(_ -> coin.set(Coin.FIFTY_CENTS));
            ONE_DOLLAR.addActionListener(_ -> coin.set(Coin.ONE_DOLLAR));
            TWO_DOLLARS.addActionListener(_ -> coin.set(Coin.TWO_DOLLARS));
            FIVE_DOLLARS.addActionListener(_ -> coin.set(Coin.FIVE_DOLLARS));
            CANCEL.addActionListener(_ -> coin.set(Coin.CANCEL));

            if(coin.get() != null){
                setVisible(false);
                return coin.get();
            }
        }
    }

}
