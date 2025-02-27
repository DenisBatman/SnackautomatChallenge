package ch.noseryoung.blj;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UI extends JPanel implements Runnable {
    public  final int WIDTH = 1100;
    public  final int HEIGHT = 800;
    private final int MARGIN = 100;
    private final int TOP_LEFT_X = 250;
    private final int TOP_LEFT_Y = 160;
    private ProductSort selectedProduct;
    Customer customer;

    public VendingMachine vendingMachine;

    MovementMouse mouse = new MovementMouse();
    final int FPS = 60;
    Thread applicationThread;

    public UI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        String name = new JFrameUserInputField("What is your name?").getString();
        boolean changedName = false;
        for (Customer customer : Menu.customers){
            if(Objects.equals(customer.getName(), name)){
                this.customer = customer;
                changedName = true;
            }
        }
        if(!changedName){
            customer = new Customer(name);
            Menu.customers.add(customer);
        }
    }

    public BufferedImage getImage (String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage background = getImage("/VendingMachineBackground");
        graphics2D.drawImage(background, 0, 0, 800,  800,null);
    }


@Override
public void run() {
    // GAME LOOP
    // 1 second in nanoseconds divided by FPS rate
    double drawInterval = (double) 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    while (applicationThread != null) {

        currentTime = System.nanoTime();

        delta += (currentTime - lastTime) / drawInterval;
        lastTime = currentTime;

        if (delta >= 1) {
            update();
            repaint();
            delta--;
        }

    }
}

    private void update() {
        if(mouse.isPressed){
            if (vendingMachine.currentProduct == null) {
                for (ProductSort productSort : vendingMachine.productSorts) {
                    //if mouse position is on product
                    if((mouse.x >= 50 - productSort.x && mouse.x <= 50 + productSort.x) &&
                            (mouse.y >= 50 - productSort.y && mouse.y <= 50 + productSort.y)){
                        selectedProduct = productSort;
                        break;
                    }
                }
            }
        }
        if(!mouse.isPressed){
            if(selectedProduct != null){
                vendingMachine.currentProduct = selectedProduct;
                selectedProduct = null;
                int amount = new JFrameUserInputField("How much " + vendingMachine.currentProduct.getName()
                        + " do you want?").getInteger();
                System.out.println(amount);
                if(amount <= vendingMachine.currentProduct.products.size()){
                    double totalPrice = vendingMachine.currentProduct.getPrice() * amount;
                    payment(totalPrice, amount);
                    vendingMachine.currentProduct = null;
                }
            }
        }
    }

    private void payment(double totalPrice, int amount) {
        vendingMachine.isInPayment(vendingMachine.currentProduct.getName(), customer, totalPrice, amount);
    }

    public void launchApplication() {
        applicationThread = new Thread(this);
        applicationThread.start();
    }

    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        draw(graphics2D);
        int x = 250;
        int y = 170;
        int counter = 0;
        for(ProductSort productSort : vendingMachine.productSorts){
            productSort.draw(graphics2D, x, y);
            counter++;
            if(counter % 3 == 0){
                x = 250;
                y += 90;
            } else {
                x += MARGIN;
            }
        }
        // status messages
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setFont(new Font("Century Gothic", Font.PLAIN, 45));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Your credit: " + customer.getCredit(), 700, 100);
        graphics2D.drawString(customer.name, 700, 700);
        if(vendingMachine.currentProduct == null){
            graphics2D.drawString("Select a product!", 700, 400);
        }

    }



}