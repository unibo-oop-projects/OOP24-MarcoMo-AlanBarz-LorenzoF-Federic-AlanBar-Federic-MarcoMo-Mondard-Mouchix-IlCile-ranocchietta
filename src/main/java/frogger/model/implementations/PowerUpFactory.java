package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;

public class PowerUpFactory {

    public static AbstractPowerUp createPowerUp(PowerUpType type, Position position, Pair dimension) {
        switch (type) {            
            case EXTRA_LIFE -> {
                return new ExtraLifePowerUp(position, dimension);
            }
            default -> throw new IllegalArgumentException("Unknown power-up type: " + type);
        }
    }

    public static PowerUpType getRandomPowerUpType() {
        // PowerUpType[] powerUpTypes = PowerUpType.values();
        // java.util.Random random = new java.util.Random();
        // int randomIndex = random.nextInt(powerUpTypes.length);
        // return powerUpTypes[randomIndex];
        return PowerUpType.EXTRA_LIFE; // For now, only one power-up type is available
    }
}
