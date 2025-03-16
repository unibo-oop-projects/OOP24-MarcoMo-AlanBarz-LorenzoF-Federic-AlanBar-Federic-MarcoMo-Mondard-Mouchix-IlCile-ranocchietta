package frogger.model.interfaces;

import frogger.common.Direction;

public interface MovingObject extends GameObject{
    public void setDirection(Direction direction);

    public Direction getDirection();

    public float getSpeed();

    public boolean move();
}
