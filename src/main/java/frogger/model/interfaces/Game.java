package frogger.model.interfaces;

import frogger.common.Position;

public interface Game {

    boolean isGameOver();

    int getScore();

    void checkCollision(int pixel);
}
