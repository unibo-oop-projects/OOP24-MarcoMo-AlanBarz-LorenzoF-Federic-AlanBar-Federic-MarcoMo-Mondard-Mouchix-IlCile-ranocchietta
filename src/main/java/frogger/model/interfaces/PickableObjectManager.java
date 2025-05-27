package frogger.model.interfaces;

import java.util.ArrayList;

import frogger.model.implementations.PickableObjectImpl;

public interface PickableObjectManager {

    ArrayList<PowerUp> getActivePowerUps();

    void addPickableObject(PickableObjectImpl x);

}