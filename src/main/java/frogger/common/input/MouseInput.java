package frogger.common.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.controller.MenuController;


public class MouseInput implements MouseMotionListener, MouseListener {
    private MenuController controller;
    
    public <X extends MenuController> MouseInput(X controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.getMenu().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.getMenu().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
        controller.getMenu().mouseMoved(e);
    }
}