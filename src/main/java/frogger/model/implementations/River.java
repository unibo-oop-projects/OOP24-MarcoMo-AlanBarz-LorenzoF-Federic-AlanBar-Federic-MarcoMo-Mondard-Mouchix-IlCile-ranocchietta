package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

public class River extends AbstractLaneImpl {

    public River(float speed, Direction direction) {
        super(speed, direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCar(MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrunk(final MovingObject obstacle) {
        super.obstacles.add((Trunk)obstacle);
    }
}
