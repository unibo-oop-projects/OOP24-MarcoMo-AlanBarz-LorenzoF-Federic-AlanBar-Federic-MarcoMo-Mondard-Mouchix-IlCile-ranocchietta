package frogger.model.interfaces;

import java.util.List;

import frogger.common.Direction;

public interface Lane {
    void addMovingObject(MovingObject obstacle);

    void setSpeed(int speed);

    void setDirection(Direction direction);

    List<MovingObject> getLaneObstacles();
}
