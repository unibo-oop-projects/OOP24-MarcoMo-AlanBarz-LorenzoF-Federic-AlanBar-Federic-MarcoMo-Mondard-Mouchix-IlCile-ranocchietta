
package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;

import frogger.model.interfaces.PowerUp;
import frogger.model.interfaces.PowerUpMenager;

public class PowerUpMenagerImpl implements PowerUpMenager {
    private final List<PowerUp> activePowerUps = new ArrayList<>();
    
    public PowerUpMenagerImpl() {}
   
    @Override
    public void addPowerUp(PowerUp powerUp) {
        if (powerUp != null) {
            activePowerUps.add(powerUp);
        }
    }
    
    @Override
    public ArrayList<PowerUp> getActivePowerUps() {    
        // Filter out inactive power-ups
        activePowerUps.removeIf(powerUp -> !powerUp.isActive());   
        return new ArrayList<>(activePowerUps);
    }
}
