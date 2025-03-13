package frogger.model.interfaces;

import java.util.Optional;

import frogger.model.implementations.PlayerObjectImpl;

public interface Trunk extends MovingObject {
    
    void setFrog(Optional<PlayerObjectImpl> frog);

    void removeFrog();
}
