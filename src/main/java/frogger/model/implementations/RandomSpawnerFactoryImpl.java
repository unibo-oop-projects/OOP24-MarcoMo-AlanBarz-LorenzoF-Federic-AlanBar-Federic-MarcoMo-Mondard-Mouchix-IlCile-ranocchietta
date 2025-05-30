package frogger.model.implementations;

import java.util.Random;
import java.util.Set;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.EntitySpawner;
import frogger.model.interfaces.PickableObject;
import frogger.model.interfaces.RandomSpawnerFactory;

/**
 * {@inheritDoc}.
 */
public class RandomSpawnerFactoryImpl implements RandomSpawnerFactory {

    private final Random ran = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySpawner<Car> randomCarSpawner(final int laneIndex, final float speed, final Direction direction) {
        return new RandomObstaclesSpawner<>(Car.class, laneIndex, speed, direction, ran);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySpawner<Trunk> randomTrunkSpawner(final int laneIndex, final float speed, final Direction direction) {
        return new RandomObstaclesSpawner<>(Trunk.class, laneIndex, speed, direction, ran);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySpawner<Eagle> randomEagleSpawner() {
        return new RandomEaglesSpawner(ran);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySpawner<PickableObject> randomPowerUpSpawner(Set<Position> alreadyPresent) {
        return new RandomPickableSpawner(ran, PowerUpImpl.class, alreadyPresent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntitySpawner<PickableObject> randomCoinSpawner(Set<Position> alreadyPresent) {
        return new RandomPickableSpawner(ran, Coin.class, alreadyPresent);
    }

}
