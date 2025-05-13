package frogger.model.implementations;

import frogger.model.interfaces.PurchasableObjectFactory;

public class PurchasableObjectFactoryImpl implements PurchasableObjectFactory{

    @Override
    public Skin createSkin(int prize, String img, boolean isBought) {
        return new Skin(prize, img, isBought);
    }
}
