package frogger.view;

import javax.swing.JPanel;

public abstract class AbstractPanel<X> extends JPanel{
    private X controller;
    
    protected abstract void setInputListener();

    protected abstract void importImg();

    public void setController(X controller) {
        this.controller = controller;
        this.importImg();
        this.setInputListener();
    }

    protected X getController() {
        return controller;
    }
}
