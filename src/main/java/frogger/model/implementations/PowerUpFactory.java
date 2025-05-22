package frogger.model.implementations;

import java.util.Random;

import frogger.common.Pair;
import frogger.common.Position;

public class PowerUpFactory {

    public static AbstractPowerUp createPowerUp(PowerUpType type, Position position, Pair dimension) {
        switch (type) {            
            case EXTRA_LIFE -> {
                return new ExtraLifePowerUp(position, dimension);
            }
            case FREEZE -> {
                return new FreezePowerUp(position, dimension);
            }
            default -> throw new IllegalArgumentException("Unknown power-up type: " + type);
        }
    }

    public static PowerUpType getRandomPowerUpType() {
        PowerUpType[] powerUpTypes = PowerUpType.values();
        Random random = new Random();
        int randomIndex = random.nextInt(powerUpTypes.length);
        return powerUpTypes[randomIndex];      
    }
    
}
