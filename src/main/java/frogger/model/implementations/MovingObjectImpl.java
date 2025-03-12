package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.MovingObject;

public class MovingObjectImpl extends GameObjectImpl implements MovingObject{
    private Direction direction;
    private float speed;

    public MovingObjectImpl(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension);
        this.direction = direction; 
        this.speed = speed;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void move() {
        this.setPos(new Position(this.getPos().x() + this.getDirectionValue().x() * this.getSpeed(), 
        this.getPos().y() + this.getDirectionValue().y() * this.getSpeed()));
    }

    public Position getDirectionValue() {
        int x = 0;
        int y = 0;

        switch(this.getDirection()) {
            case Direction.LEFT:
                x = -1;
                y = 0;
                break;
            case Direction.RIGHT:
                x = 1;
                y = 0;
                break;
            case Direction.UP:
                x = 0;
                y = -1;
                break;
            case Direction.DOWN:
                x = 0;
                y = 1;
                break;
                
        }
        return new Position(x, y);
    }
}
