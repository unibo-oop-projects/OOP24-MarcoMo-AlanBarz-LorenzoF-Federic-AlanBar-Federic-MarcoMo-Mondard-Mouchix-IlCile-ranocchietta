package frogger.model.interfaces;

import java.awt.image.BufferedImage;

import frogger.model.implementations.Skin;

public interface PurchasableObjectFactory {

    Skin createSkin(int prize, BufferedImage img, boolean isBought);
}
