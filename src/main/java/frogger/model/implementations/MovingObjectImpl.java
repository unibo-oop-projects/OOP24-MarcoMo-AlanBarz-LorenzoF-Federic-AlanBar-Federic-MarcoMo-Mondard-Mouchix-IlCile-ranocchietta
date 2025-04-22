package frogger.model.implementations;

import ch.qos.logback.core.ConsoleAppender;
import frogger.common.Constants;
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
        this.checkRestart();
    }

    private void checkRestart(){
        if(!valid(this.getPos())) {
            switch (this.getDirection()) {
                case Direction.RIGHT -> this.setPos(new Position(Constants.MIN_X - 1, this.getPos().y()));
                case Direction.LEFT -> this.setPos(new Position(Constants.MAX_X + 1, this.getPos().y()));
                case Direction.UP -> this.setPos(new Position(this.getPos().x(), Constants.MIN_Y - 1));
                case Direction.DOWN -> this.setPos(new Position(this.getPos().x(), Constants.MAX_Y + 1));
            }

            //to stop the eagles once they are arrived at the end of the column
            if(this instanceof Eagle) {
                Eagle e = (Eagle)this;
                e.stop();
            }
        }
    }

    private boolean valid(final Position pos) {
        return pos.x() >= Constants.MIN_X - 1 && pos.x() <= Constants.MAX_X + 1 &&
        pos.y() >= Constants.MIN_Y - 1 && pos.y() <= Constants.MAX_Y + 1;
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
                y = 1;
                break;
            case Direction.DOWN:
                x = 0;
                y = -1;
                break;
                
        }
        return new Position(x, y);
    }
}
