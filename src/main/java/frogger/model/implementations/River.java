package frogger.model.implementations;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.Trunk;

public class River extends AbstractLaneImpl {

    public River(float speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    public void addCar(MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");    
    }

    @Override
    public void addTrunk(final MovingObject obstacle) {
        super.obstacles.add((Trunk)obstacle);
    }
}
