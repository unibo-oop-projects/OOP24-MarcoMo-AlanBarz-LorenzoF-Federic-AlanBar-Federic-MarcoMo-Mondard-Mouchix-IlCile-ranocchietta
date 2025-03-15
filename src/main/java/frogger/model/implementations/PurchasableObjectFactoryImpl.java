package frogger.model.implementations;

import java.awt.image.BufferedImage;

import frogger.model.interfaces.PurchasableObject;
import frogger.model.interfaces.PurchasableObjectFactory;

public class PurchasableObjectFactoryImpl implements PurchasableObjectFactory{

    @Override
    public PurchasableObject createSkin(int prize, BufferedImage img) {
        return new Skin(prize, img);
    }
}
