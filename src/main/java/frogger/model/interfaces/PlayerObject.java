package frogger.model.interfaces;

import frogger.common.Direction;

public interface PlayerObject {

    int getLives();

    void getHit();

    Direction getDirection();

    void setLookingRight();

    void setLookingLeft();

    void setLookingDown();

    void setLookingUp();

    void addPoints(int points);

    int getScore();

    void resetPosition();
}
