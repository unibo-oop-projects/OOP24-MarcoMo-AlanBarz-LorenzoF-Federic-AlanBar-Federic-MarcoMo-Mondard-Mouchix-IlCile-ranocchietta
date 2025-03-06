package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.Trunk;

public class River extends AbstractLaneImpl {

    public River(int speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    public void addMovingObject(MovingObject obstacle) {
        if (obstacle instanceof Trunk) {
            obstacles.add(obstacle);
        }

        throw new IllegalArgumentException("MovingObject type is incopatible with River.");
    }

}
