package frogger.model.interfaces;

import java.util.Set;

public interface Shop {

    void shopInit();
    
    Set<PurchasableObject> getPurchasableObject();
}
