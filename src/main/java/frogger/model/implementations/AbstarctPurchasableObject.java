package frogger.model.implementations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.model.interfaces.PurchasableObject;

public abstract class AbstarctPurchasableObject implements PurchasableObject {
    private final int prize;
    private final BufferedImage img;
    private final boolean isBought;

    public AbstarctPurchasableObject(int prize, BufferedImage img, boolean isBought) {
        this.prize = prize;
        this.img = img;
        this.isBought = isBought;
    }

    @Override
    public int getPrize() {
        return this.prize;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        System.out.println("Drawing purchasable object at: " + x + ", " + y);
        g.drawImage(this.img, x, y, img.getWidth(), img.getHeight(), null);
        if(this.isBought){
            g.drawString("Bought", 0, 0);
        } else {
            g.drawString("Not Bought", 0, 0);
        }
    }

    @Override
    public BufferedImage getBufferedImage() {
        return this.img;
    }

}
