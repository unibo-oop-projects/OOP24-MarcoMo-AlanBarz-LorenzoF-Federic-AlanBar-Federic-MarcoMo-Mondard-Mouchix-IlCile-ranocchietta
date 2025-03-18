package frogger.model.implementations;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import frogger.model.interfaces.PurchasableObject;
import frogger.model.interfaces.Shop;

public class ShopImpl implements Shop{

    private final Set<PurchasableObject> purchasableObjects = new HashSet<>();
    private final PurchasableObjectFactoryImpl factory = new PurchasableObjectFactoryImpl();

    @Override
    public Set<PurchasableObject> getPurchasableObject() {
        return this.purchasableObjects;
    }

    @Override
    public void shopInit() {
        try {
             this.purchasableObjects.add(factory.createSkin(10, ImageIO.read(getClass().getResourceAsStream("/ranocchietta.png"))));
        } catch (IOException e) {
             e.printStackTrace();
        }
    }

}
