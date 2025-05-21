package frogger.model.interfaces;

import frogger.common.Direction;
import frogger.model.implementations.Car;
import frogger.model.implementations.Eagle;
import frogger.model.implementations.Trunk;

/**
 * Factory interface for creating different types of {@link EntitySpawner} objects.
 * This interface centralizes the creation of spawner instances for various game entities
 * allowing for a single point of construction, the goal is to avoid code repetition and improve 
 * maintainability
 */
public interface RandomSpawnerFactory {
    /**
     * return a spawner for Car entity,
     * @param laneIndex the index of the lane
     * @param speed the speed
     * @param direction the direction
     * @return the spawner object
     */
    EntitySpawner<Car> randomCarSpawner(final int laneIndex, final float speed, final Direction direction);

    /**
     * return a spawner for Trunk entity,
     * @param laneIndex the index of the lane
     * @param speed the speed
     * @param direction the direction
     * @return the spawner object
     */
    EntitySpawner<Trunk> randomTrunkSpawner(final int laneIndex, final float speed, final Direction direction);

    /**
     * return a spawner for Eagle entity,
     */
    EntitySpawner<Eagle> randomEagleSpawner();

    /**
     * return a spawner for PowerUp entity,
     */
    EntitySpawner<PowerUp> randomPowerUpSpawner();
}
