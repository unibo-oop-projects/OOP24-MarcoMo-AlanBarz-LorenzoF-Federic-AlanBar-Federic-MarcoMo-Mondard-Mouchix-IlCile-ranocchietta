package frogger.model.interfaces;

import frogger.model.implementations.PlayerObjectImpl;

public interface Carrier { 
    
    void setFrog(PlayerObjectImpl frog);

    void removeFrog();
}
