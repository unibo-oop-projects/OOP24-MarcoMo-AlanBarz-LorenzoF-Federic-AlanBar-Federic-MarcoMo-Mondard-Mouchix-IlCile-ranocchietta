package frogger.model.implementations;

import java.util.HashSet;
import java.util.Set;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;

public class Ground implements Lane{

    @Override
    public void addMovingObject(MovingObject obstacle) {
        throw new UnsupportedOperationException("The ground lane must not have any obstacles.");
    }

    @Override
    public int getSpeed() {
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

}
