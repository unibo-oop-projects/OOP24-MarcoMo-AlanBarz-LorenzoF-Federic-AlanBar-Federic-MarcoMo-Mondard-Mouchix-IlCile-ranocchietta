package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.MovingObjectFactory;

public class MovingObjectFactoryImpl implements MovingObjectFactory{

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
    @Override
    public <X extends MovingObjectImpl> X createMovingObject(final Position pos, final Pair dimension, 
        final float speed, final Direction direction, final Class<X> c) {
        
        try {
            return c.getConstructor(Position.class, Pair.class, float.class, Direction.class)
            .newInstance(pos, dimension, speed, direction);
        } catch (NullPointerException ex) {
            System.out.println("error: " + ex.getMessage());
            return null;
        } catch (RuntimeException ex) {
            System.out.println("error: " + ex.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
            return null;
        }
    }

}
