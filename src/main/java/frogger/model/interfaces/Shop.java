package frogger.model.interfaces;

import java.util.List;

public interface Shop {

    /**
     * Returns a copy of the list of purchasable objects available in the shop.
     *
     * @return a new list containing the purchasable objects
     */
    public List<PurchasableObject> getPurchasableObjects();

    /**
     * Initializes the shop by loading purchasable objects from the save file if it exists,
     * or from the default resource file otherwise.
     * Populates the internal list of purchasable objects.
     */
    public void init();

    /**
     * Saves the current state of purchasable objects to the shop save file.
     * Uses UTF-8 encoding for writing.
     */
    public void update();
}
