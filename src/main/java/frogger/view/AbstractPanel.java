package frogger.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import frogger.common.Constants;

public abstract class AbstractPanel<X> extends JPanel{
    private X controller;
    private BufferedImage background;
    protected abstract void setInputListener();

    protected abstract void importImg();

    public void setController(X controller) {
        this.controller = controller;
        this.importImg();
        this.setInputListener();
    }

    protected void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    protected X getController() {
        return controller;
    }

    protected void paintBackground(Graphics g) {
        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);
    }

    protected BufferedImage getBackgroundImage() {
        return background;
    }

    protected void setBackgroundImage(BufferedImage background) {
        this.background = background;
    }
}
