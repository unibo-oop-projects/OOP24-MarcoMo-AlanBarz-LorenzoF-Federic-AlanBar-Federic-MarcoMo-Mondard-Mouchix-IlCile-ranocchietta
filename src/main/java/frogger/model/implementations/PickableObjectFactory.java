package frogger.model.implementations;

import java.util.Random;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PickableObject;

public class PickableObjectFactory {

    public static PickableObject createPickableObject(Class<? extends PickableObject> type, Position position, Pair dimension) {
        return switch (type.getSimpleName()) {
            case "Coin" -> new Coin(position, dimension);
            case "PowerUpImpl" -> getRandomPowerUpType(position, dimension);
            default -> null;
        };
    }

    private static PickableObject getRandomPowerUpType(Position position, Pair dimension) {
        Random random = new Random();
        int rand = random.nextInt(1,101); // 1-100
        PowerUpType selectedType;

        if (rand < 95) { // 95% FREEZE or EXTRA_LIFE
            selectedType = random.nextBoolean() ? PowerUpType.FREEZE : PowerUpType.EXTRA_LIFE;
        } else { // 5% X2_SCORE
            selectedType = PowerUpType.X2_SCORE;
        }

        return switch (selectedType) {
            case FREEZE -> new FreezePowerUp(position, dimension, 3);
            case EXTRA_LIFE -> new ExtraLifePowerUp(position, dimension, 0);
            case X2_SCORE -> new DoubleScorePowerUp(position, dimension, 0);
        };
    }
    
}
