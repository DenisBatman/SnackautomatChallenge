package ch.noseryoung.blj;


import java.awt.*;
import javax.swing.*;

public class UI extends JPanel implements Runnable{
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    MovementMouse mouse = new MovementMouse();
    final int FPS = 60;
    Thread applicationThread;

    public UI() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
    }

    @Override
    public void run() {
        // GAME LOOP
        // 1 second in nanoseconds divided by FPS rate
        double drawInterval = 1000000000 / FPS;
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