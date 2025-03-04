package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.Eagle;

public class EagleImpl extends MovingObjectImpl implements Eagle{

    public EagleImpl(Position pos, int dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
    }

}
