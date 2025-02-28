package frogger.model.implementations;

import frogger.common.Position;
import frogger.model.interfaces.PlayerObject;

public class PlayerObjectImpl extends GameObjectImpl implements PlayerObject{

    private boolean up, down, left, right;
    private int lives;

    public PlayerObjectImpl(Position pos, int dimension) {
        super(pos, dimension);
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public void getHit() {
        this.lives--;
    }

    @Override
    public boolean isLookingRight() {
        return this.right;
    }

    @Override
    public void setLookingRight(boolean bool) {
        this.right = bool;
    }

    @Override
    public boolean isLookingLeft() {
        return this.left;
    }

    @Override
    public void setLookingLeft(boolean bool) {
        this.left = bool;
    }

    @Override
    public boolean isLookingDown() {
        return this.down;
    }

    @Override
    public void setLookingDown(boolean bool) {
        this.down = bool;
    }

    @Override
    public boolean isLookingUp() {
        return this.up;
    }

    @Override
    public void setLookingUp(boolean bool) {
        this.up = bool;
    }
}
