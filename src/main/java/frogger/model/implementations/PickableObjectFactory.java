package frogger.model.implementations;

import java.util.Random;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PickableObject;

public class PickableObjectFactory {

    public static PickableObject createPickableObject(Class<? extends PickableObject> type, Position position, Pair dimension) {
        return switch (type.getSimpleName()) {
            case "Coin" -> new Coin(position, dimension);
            case "PowerUp" -> getRandomPowerUpType(position, dimension);
            default -> null;
        };
    }

    private static PickableObject getRandomPowerUpType(Position position, Pair dimension) {
        PowerUpType[] powerUpTypes = PowerUpType.values();
        Random random = new Random();
        int randomIndex = random.nextInt(powerUpTypes.length);
        PowerUpType selectedType = powerUpTypes[randomIndex];
        return switch (selectedType) {
            case FREEZE -> new FreezePowerUp(position, dimension, 3);
            case EXTRA_LIFE -> new ExtraLifePowerUp(position, dimension);
        };
    }
    
}
