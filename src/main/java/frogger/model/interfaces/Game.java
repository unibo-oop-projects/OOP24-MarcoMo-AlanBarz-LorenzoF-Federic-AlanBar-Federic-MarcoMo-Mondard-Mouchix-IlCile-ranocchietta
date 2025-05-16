package frogger.model.interfaces;

import java.util.List;

public interface Game {

    boolean isGameOver();

    int getScore();

    PlayerObject getPlayer();

    /**
     * Get all the obstacles in the level
     * @return the list of obstacles
     */
    List<MovingObject> getObstacles();

    /**
     * Get the level
     * @return the level
     */
    Level getLevel();

    void checkCollision();

    void checkNewLevel();

    /**
     * Get the lane where the frog is at the moment
     * @return the lane
     */
    Lane getCurrentLane();

    /**
     * Check if the current lane is already completed, if not add points to the frog
     */
    void checkProgress();

    /**
     * Check if the game is currently paused
     * @return true if it is, false otherwise
     */
    boolean gameIsPaused();

    List<PowerUp> getPowerUps();
}
