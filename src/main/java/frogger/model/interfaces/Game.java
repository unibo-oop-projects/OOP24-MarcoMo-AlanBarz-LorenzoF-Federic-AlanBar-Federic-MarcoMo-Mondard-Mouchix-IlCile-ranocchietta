package frogger.model.interfaces;

import java.util.Set;

public interface Game {

    boolean isGameOver();

    int getScore();

    PlayerObject getPlayer();

    Set<MovingObject> getObstacles();

    Level getLevel();

    void checkCollision();

    void checkNewLevel();
}
