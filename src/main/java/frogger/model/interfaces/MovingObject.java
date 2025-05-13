package frogger.model.interfaces;

import frogger.common.Direction;

public interface MovingObject extends GameObject{
    /**
     * set the direction from @class frogger.common.Direction in whitch the Moving Object will move
     * @param direction the direction choosed
     */
    void setDirection(final Direction direction);

    /** 
     * @return the direction settled to the object
     */
    Direction getDirection();

    /**
     * @return the speed settled to the object 
     */
    float getSpeed();

    /**
     * change the position of the object in base of his direction and speed
     */
    void move();
}
