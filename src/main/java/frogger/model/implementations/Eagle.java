package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;

public class Eagle extends MovingObjectImpl {

    public Eagle(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(getClass().getResourceAsStream("/eagle.png"));
    }

}
