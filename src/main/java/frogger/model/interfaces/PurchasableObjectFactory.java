package frogger.model.interfaces;

import frogger.model.implementations.Skin;

public interface PurchasableObjectFactory {

    Skin createSkin(int prize, String img, boolean isBought);
}
