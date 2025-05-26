package frogger.common.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.controller.MenuController;


public class MouseInput implements MouseMotionListener, MouseListener {
    private MenuController controller;
    
    public <X extends MenuController> MouseInput(X controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {           
            case SHOP -> {       
            // Handle shop event    
            }
            case DEAD -> {
            // Handle quit event
            } 
            default -> {
                // Handle default case
            }           
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.getMenu().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.getMenu().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit event
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Handle mouse drag event
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        controller.getMenu().mouseMoved(e);
    }
}