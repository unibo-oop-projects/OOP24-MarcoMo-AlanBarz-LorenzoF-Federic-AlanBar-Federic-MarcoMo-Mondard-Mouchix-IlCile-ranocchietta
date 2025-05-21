package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.EntitySpawner;
import frogger.model.interfaces.PowerUp;

/**
 * {@inheritDoc}
 */
public class RandomSpawnerFactoryImpl  {
    
    /**
     * {@inheritDoc}
     */
    public EntitySpawner<Car> randomCarSpawner(final int laneIndex, final float speed, final Direction direction) {
        return new RandomObstaclesSpawner<>(Car.class, laneIndex, speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    public EntitySpawner<Trunk> randomTrunkSpawner(final int laneIndex, final float speed, final Direction direction) {
        return new RandomObstaclesSpawner<>(Trunk.class, laneIndex, speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    public EntitySpawner<Eagle> randomEagleSpawner() {
        return new RandomEaglesSpawner();
    }

    /**
     * {@inheritDoc}
     */
    public EntitySpawner<PowerUp> randomPowerUpSpawner() {
        return new RandomPowerUpsSpawner();
    }
}
