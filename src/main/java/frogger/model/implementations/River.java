package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

/**
 * Class that extends AbstractLaneImpl to specify the behaviour for River type Lane, in particular to add Trunk type obstacles.
 */
public class River extends AbstractLaneImpl {

    public River(final float speed, final Direction direction) {
        super(speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCar(final MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrunk(final MovingObject obstacle) {
        super.obstacles.add((Trunk) obstacle);
    }
}
