package frogger.model;

import frogger.common.Position;
import frogger.model.implementations.GameObjectImpl;

public class MovingObject extends GameObjectImpl {
    private int direction;
    private float speed;

    public MovingObject(Position pos, int dimension, float speed, int direction) {
        super(pos, dimension);
        this.direction = direction; 
        this.speed = speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }
}
