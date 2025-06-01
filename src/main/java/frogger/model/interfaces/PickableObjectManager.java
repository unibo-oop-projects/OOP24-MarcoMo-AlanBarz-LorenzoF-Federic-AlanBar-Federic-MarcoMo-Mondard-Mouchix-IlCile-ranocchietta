package frogger.model.interfaces;

import java.util.ArrayList;

import frogger.model.implementations.PickableObjectImpl;

public interface PickableObjectManager {

    void checkPowerUps();

    void addPickableObject(PickableObjectImpl x);

    ArrayList<PowerUp> getActivePowerUps();
}