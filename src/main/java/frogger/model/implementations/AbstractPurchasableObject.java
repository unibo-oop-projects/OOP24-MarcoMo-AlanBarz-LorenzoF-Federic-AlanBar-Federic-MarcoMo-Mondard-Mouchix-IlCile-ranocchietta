package frogger.model.implementations;

import frogger.model.interfaces.PurchasableObject;

public abstract class AbstractPurchasableObject implements PurchasableObject {
    private final int prize;
    private final String img;
    private final boolean isBought;

    public AbstractPurchasableObject(int prize, String img, boolean isBought) {
        this.prize = prize;
        this.img = img;
        this.isBought = isBought;
    }

    @Override
    public int getPrize() {
        return this.prize;
    }

    @Override
    public String getImage() {
        return this.img;
    }

    @Override
    public boolean isAvailable() {
        return this.isBought;
    }

}
