package frogger.model.interfaces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface PurchasableObject {

    int getPrize();

    BufferedImage getBufferedImage();

    boolean isAvailable();

    void draw(Graphics g, int x, int y);
}
