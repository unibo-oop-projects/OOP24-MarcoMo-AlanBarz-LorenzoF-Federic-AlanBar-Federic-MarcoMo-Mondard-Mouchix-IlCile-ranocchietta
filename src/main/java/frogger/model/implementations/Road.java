package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

/**
 * Class that extends AbstractLaneImpl to specify the behaviour for Road type Lane, in particular to add Car type obstacles.
 */
public class Road extends AbstractLaneImpl {

    public Road(final float speed, final Direction direction) {
        super(speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCar(final MovingObject obstacle) {
        super.obstacles.add((Car) obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrunk(final MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");
    }
}
