package frogger.model.interfaces;

import frogger.model.implementations.PickableObjectImpl;

public interface PickableObjectManager {

    void checkPowerUps();
    void addPickableObject(PickableObjectImpl x);
}