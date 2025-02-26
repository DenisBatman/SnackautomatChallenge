package ch.noseryoung.blj;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovementMouse extends MouseAdapter {
    public int x, y;
    public boolean isPressed;
    @Override
    public void mousePressed(MouseEvent event){
        isPressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent event){
        isPressed = false;
    }
    @Override
    public void mouseDragged(MouseEvent event){
        x = event.getX();
        y = event.getY();
    }
    @Override
    public void mouseMoved(MouseEvent event){
        x = event.getX();
        y = event.getY();
    }

}