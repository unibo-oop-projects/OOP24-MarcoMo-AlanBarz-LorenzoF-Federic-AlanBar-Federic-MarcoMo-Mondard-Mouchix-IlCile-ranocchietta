package frogger.model.interfaces;

import frogger.common.Direction;

public interface MovingObject {
    public void setDirection(Direction direction);

    public Direction getDirection();

    public float getSpeed();

    public void move();
}
