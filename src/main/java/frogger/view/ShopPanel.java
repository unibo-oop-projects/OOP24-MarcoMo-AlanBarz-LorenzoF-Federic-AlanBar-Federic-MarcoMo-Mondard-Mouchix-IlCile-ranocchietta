package frogger.view;

 
import java.awt.Color;

import frogger.controller.ShopController;

public class ShopPanel extends AbstractPanel<ShopController>{

    public ShopPanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.CYAN);
    }

    @Override
    protected void setInputListener() {
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());     
    }

    @Override
    public void paintComponent(final java.awt.Graphics g) {
        //AtomicInteger counterX = new AtomicInteger(0);
        //AtomicInteger counterY = new AtomicInteger(0);
        /*this.getController().getPurchasableObject().stream().forEach(purchasableObject -> {
            purchasableObject.draw(g, counterX.get(), counterY.get());
            if(counterY.get() < 3){
                counterY.incrementAndGet();
            } else {
                counterY.set(0);
                counterX.incrementAndGet();
            }
            counterX.incrementAndGet();
        });*/
        //this.getController().getPurchasableObject().stream().findFirst().get().draw(g, 0, 0);
    }

    @Override
    protected void importImg() {
        // No images to import for the shop panel
    }
}
