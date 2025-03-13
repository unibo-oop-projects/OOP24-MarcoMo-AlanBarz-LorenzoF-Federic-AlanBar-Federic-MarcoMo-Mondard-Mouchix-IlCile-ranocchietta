package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PlayerObject;

public class PlayerObjectImpl extends GameObjectImpl implements PlayerObject{

    private final Position startPosition = new Position(0, -6);

    private Direction direction;
    private int lives;
    private int score;

    public PlayerObjectImpl(Pair dimension) {
        super(new Position(0, -6), dimension);
        this.lives = 3;
        this.score = 0;
        this.direction = Direction.UP;
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public void getHit() {
        this.resetPosition();
        this.lives--;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setLookingRight() {
        this.direction = Direction.RIGHT;
    }

    @Override
    public void setLookingLeft() {
        this.direction = Direction.LEFT;
    }

    @Override
    public void setLookingDown() {
        this.direction = Direction.DOWN;
    }

    @Override
    public void setLookingUp() {
        this.direction = Direction.UP;
    }

    @Override
    public void addPoints(int points) {
        this.score+=points;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void resetPosition() {
        setLookingUp();
        setPos(startPosition);
    }
}
