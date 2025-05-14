package frogger.model.implementations;

import java.util.HashSet;
import java.util.Set;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;

/**
 * Implements the interface Lane, model the start, mid and last lane of the level.
 */
public final class Ground implements Lane {

    @Override
    public void addMovingObject(final MovingObject obstacle) {
        throw new UnsupportedOperationException("The ground lane must not have any obstacles.");
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public Set<MovingObject> getLaneObstacles() {
        return new HashSet<>();
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void setCompleted() { }

}
