package frogger.model.interfaces;

import java.util.ArrayList;

public interface PowerUpMenager {

    void addPowerUp(PowerUp powerUp);

    ArrayList<PowerUp> getActivePowerUps();

}