package frogger.model.interfaces;

import java.util.List;

import frogger.model.implementations.PickableObjectImpl;

public interface PickableObjectManager {

    void checkPowerUps();

    void addPickableObject(PickableObjectImpl x);

    List<PowerUp> getActivePowerUps();
}