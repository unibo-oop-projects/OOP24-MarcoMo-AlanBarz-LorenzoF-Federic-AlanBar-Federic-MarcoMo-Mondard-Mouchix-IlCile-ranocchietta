package frogger.model.implementations;

import frogger.model.interfaces.PurchasableObject;

public abstract class AbstractPurchasableObject implements PurchasableObject {
    private final int prize;
    private final String img;
    private boolean isBought;

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

    @Override
    public void setAvailable(boolean b) {
        this.isBought = b;
    }   

    @Override 
    public String toString() {
        return this.getClass() + " " + this.prize + " " + this.img + " " + this.isBought;
    }
}
