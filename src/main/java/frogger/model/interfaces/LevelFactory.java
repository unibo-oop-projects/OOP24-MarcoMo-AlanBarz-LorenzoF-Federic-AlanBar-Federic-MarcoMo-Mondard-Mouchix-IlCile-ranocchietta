package frogger.model.interfaces;

public interface LevelFactory {

    /**
     * Creates a level instance with a random number, width and speed of obstacles.
     * @return the level
     */
    Level randomLevel();
}
