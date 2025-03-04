package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.Car;

public class CarImpl extends MovingObjectImpl implements Car{
    
    public CarImpl(Position pos, int dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
    }

}
