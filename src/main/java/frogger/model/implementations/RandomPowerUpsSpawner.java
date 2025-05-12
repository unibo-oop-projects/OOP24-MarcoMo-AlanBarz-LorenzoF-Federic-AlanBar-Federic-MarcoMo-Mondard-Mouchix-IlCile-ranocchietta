package frogger.model.implementations;

import java.util.Set;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.RandomUtils;
import frogger.model.interfaces.PowerUp;

public class RandomPowerUpsSpawner extends AbstractRandomEntitySpawner<PowerUp>{

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValidPosition(Position pos, Set<Position> used) {
        return !used.contains(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Position generatePosition() {
        return new Position(RandomUtils.randomX(), RandomUtils.randomY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PowerUp createEntity(Position pos) {
        PowerUpType type = PowerUpFactory.getRandomPowerUpType();
        Pair dim = new Pair(Constants.POWER_UP_WIDTH, Constants.POWER_UP_HEIGHT);
        return PowerUpFactory.createPowerUp(type, pos, dim);
    }

}
