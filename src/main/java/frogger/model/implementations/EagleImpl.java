package frogger.model.implementations;

import frogger.common.Position;
import frogger.model.interfaces.Eagle;

public class EagleImpl extends MovingObjectImpl implements Eagle{

    public EagleImpl(Position pos, int dimension, float speed, int direction) {
        super(pos, dimension, speed, direction);
    }

}
