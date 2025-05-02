package frogger.model.interfaces;

import java.awt.image.BufferedImage;

public interface PurchasableObjectFactory {

    PurchasableObject createSkin(int prize, BufferedImage img, boolean isBought);
}
