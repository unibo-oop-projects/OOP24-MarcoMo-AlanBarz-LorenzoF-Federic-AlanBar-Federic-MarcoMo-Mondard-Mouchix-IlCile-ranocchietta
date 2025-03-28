package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;

public class Road extends AbstractLaneImpl {

    public Road(float speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    public void addCar(MovingObject obstacle) {
        super.obstacles.add((CarImpl)obstacle);
    }

    @Override
    public void addTrunk(MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");
    }
}
