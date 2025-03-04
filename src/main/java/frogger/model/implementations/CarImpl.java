package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Car;

public class CarImpl extends MovingObjectImpl implements Car{
    
    public CarImpl(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
    }

}
