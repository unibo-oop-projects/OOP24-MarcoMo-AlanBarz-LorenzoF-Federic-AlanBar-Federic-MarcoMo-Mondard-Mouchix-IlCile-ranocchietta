package frogger.model.interfaces;

import frogger.model.implementations.PlayerObjectImpl;

public interface Trunk extends MovingObject {
    
    void setFrog(PlayerObjectImpl frog);

    void removeFrog();
}
