package frogger.model.implementations;

import frogger.model.interfaces.PurchasableObject;
import java.awt.image.BufferedImage;

public class Skin implements PurchasableObject{

    private final int prize;
    private final BufferedImage img;

    public Skin(int prize, BufferedImage img){
        this.prize = prize;
        this.img = img;
    }

    @Override
    public int getPrize() {
        return this.prize;
    }

    @Override
    public BufferedImage getBufferedImage() {
        return this.img;
    }
}
