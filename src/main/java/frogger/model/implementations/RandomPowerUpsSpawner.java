package frogger.model.implementations;

import java.util.Set;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.RandomUtils;
import frogger.model.interfaces.PowerUp;

/**
 * Class that extends AbstractRandomEntitySpawner to specify the behaviour spawning type PowerUp entity.
 */
public class RandomPowerUpsSpawner extends AbstractRandomEntitySpawner<PowerUp> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValidPosition(final Position pos, final Set<Position> used) {
        return !used.contains(pos) || pos.y() != Constants.MIN_Y;
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
    protected PowerUp createEntity(final Position pos) {
        final PowerUpType type = PowerUpFactory.getRandomPowerUpType();
        final Pair dim = new Pair(Constants.POWER_UP_WIDTH, Constants.POWER_UP_HEIGHT);
        return PowerUpFactory.createPowerUp(type, pos, dim);
    }

}
