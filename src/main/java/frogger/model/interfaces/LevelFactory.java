package frogger.model.interfaces;

/**
 * Factory that encapsulate the different type of level that can be created,
 * by default there is only the random type but is useful to ampliate the project if needed.
 */
public interface LevelFactory {

    /**
     * Creates a level instance with a random number, width and speed of obstacles.
     * @return the level
     */
    Level randomLevel();
}
