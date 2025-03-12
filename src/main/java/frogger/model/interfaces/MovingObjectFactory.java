package frogger.model.interfaces;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.MovingObjectImpl;

public interface MovingObjectFactory {

    /**
     * generic method to create a MovingObject element
     * @param <X> type of the MovingObject
     * @param pos
     * @param dimension
     * @param speed
     * @param direction
     * @param c java.lang.Class to instance the new MovingObject
     * @return An object of type X that extends MovingObject
     */
    <X extends MovingObjectImpl> X createMovingObject(Position pos, Pair dimension,
            double speed, Direction direction, Class<X> c);

}