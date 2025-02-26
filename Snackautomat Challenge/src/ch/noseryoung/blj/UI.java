package ch.noseryoung.blj;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
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
        int x = 20;
        int y = 20;
        for(ProductSort productSort : vendingMachine.productSorts){
            x += MARGIN;
            graphics2D.drawImage(productSort.image, x, y, SIZE, SIZE ,null);

            //buttons (funktioniert noch nicht)
            JFrame frame = new JFrame("Buttoooons");
            frame.setSize(400,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);

            JButton button = new JButton("Sigmabutton");
            button.setBounds(100,50,150,40);

            frame.add(button);
            frame.setVisible(true);
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

        draw(graphics2D);

    }



}