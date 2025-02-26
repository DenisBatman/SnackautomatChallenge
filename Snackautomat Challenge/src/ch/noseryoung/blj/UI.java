package ch.noseryoung.blj;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class UI extends JPanel implements Runnable{
    public  final int WIDTH = 1100;
    public  final int HEIGHT = 800;
    public  final int SIZE = 100;
    private final int MARGIN = 50;
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
    }

    public void draw(Graphics2D graphics2D) {
        int x = 20;
        int y = 20;
        for(ProductSort productSort : vendingMachine.productSorts){
            x += MARGIN;
            graphics2D.drawImage(productSort.image, x, y, SIZE, SIZE ,null);

        }
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

    }
    public void launchApplication() {
        applicationThread = new Thread(this);
        applicationThread.start();
    }

    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

    }



}