package frogger.common.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.controller.MenuController;


public class MouseInput implements MouseMotionListener, MouseListener {
    private final MenuController controller;

    public <X extends MenuController> MouseInput(final X controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(final MouseEvent e) { }

    @Override
    public void mousePressed(final MouseEvent e) {
        controller.getMenu().mousePressed(e);
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        controller.getMenu().mouseReleased(e);
    }

    @Override
    public void mouseEntered(final MouseEvent e) { }

    @Override
    public void mouseExited(final MouseEvent e) { }

    @Override
    public void mouseDragged(final MouseEvent e) { }

    @Override
    public void mouseMoved(final MouseEvent e) {
        controller.getMenu().mouseMoved(e);
    }
}