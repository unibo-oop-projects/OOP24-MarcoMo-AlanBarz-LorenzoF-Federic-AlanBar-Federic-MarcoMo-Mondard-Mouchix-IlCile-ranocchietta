package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import frogger.common.Constants;
import frogger.controller.MenuControllerImpl;

public class MenuPanel extends JPanel{
    MenuControllerImpl controller;

    public MenuPanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    public void setController(MenuControllerImpl controller) {
        this.controller = controller;
        this.setInputListener();
    }

    private void setInputListener(){
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        this.controller.getGame().getMenu().draw(g);
    }

    public MenuControllerImpl getController() {
        return controller;
    }
}
