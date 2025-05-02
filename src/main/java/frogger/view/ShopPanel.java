package frogger.view;

import javax.swing.JPanel;
 
import frogger.controller.ShopController;

public class ShopPanel extends AbstractPanel<ShopController>{

    private final ShopController controller;

    public ShopPanel() {
        this.controller = new ShopController();
    }

    @Override
    protected void setInputListener() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInputListener'");
    }

    @Override
    protected void importImg() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importImg'");
    }


}
