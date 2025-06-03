package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

/**
 * Implements the interface Lane, model the start, mid and last lane of the level.
 */
public final class Ground extends AbstractLaneImpl {

    private Ground(float speed, Direction direction) {
        super(speed, direction);
    }

    public Ground() {
        this(0, null);
    }

    @Override
    public void addMovingObject(final MovingObject obstacle) { }

    @Override
    public boolean isCompleted() {
        return true;
    }

}
