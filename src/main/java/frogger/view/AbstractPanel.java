package frogger.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import frogger.common.Constants;

public abstract class AbstractPanel<X> extends JPanel{
    private X controller;
    
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
}
