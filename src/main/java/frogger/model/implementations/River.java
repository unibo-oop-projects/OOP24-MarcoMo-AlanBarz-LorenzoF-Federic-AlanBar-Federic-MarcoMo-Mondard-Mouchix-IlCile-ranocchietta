package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.Trunk;

public class River extends AbstractLaneImpl {

    public River(double speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    public void addCar(MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");    
    }

    @Override
    public void addTrunk(MovingObject obstacle) {
        super.obstacles.add((Trunk)obstacle);
    }
}
