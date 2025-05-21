package frogger.model.interfaces;

import java.util.List;

import frogger.model.implementations.Eagle;

/**
 * Model the concept of a level in the game.
 */
public interface Level {

    /**
     * Get the list of lanes.
     * @return the list of lanes
     */
    List<Lane> getLanes();

    /**
     * Get all the obstacles in the level.
     * @return a list of obstacles
     */
    List<MovingObject> getAllObstacles();

    /**
     * Add a lane to the list.
     * @param lane the lane to add
     */
    void addLane(Lane lane);

    /**
     * Add an eagle to the list.
     * @param eagle the eagle to add
     */
    void addEagle(Eagle eagle);

    /**
     * Get the list of eagles.
     * @return the list of eagles
     */
    List<Eagle> getEagles();

    /**
     * Get the list of power ups present in the current level.
     * @return the list
     */
    List<PowerUp> getPowerUp();

    /**
     * Add a power up to the list.
     * @param p the power up to be added
     */
    void addPowerUp(PowerUp p);

    /**
     * Remove a power up from the list.
     * @param p the power up to be removed
     */
    void removePowerUp(PowerUp p);
}
