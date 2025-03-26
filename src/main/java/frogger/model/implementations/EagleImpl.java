package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Eagle;

public class EagleImpl extends MovingObjectImpl implements Eagle{

    public EagleImpl(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(getClass().getResourceAsStream("/carRight.png"));
    }

}
