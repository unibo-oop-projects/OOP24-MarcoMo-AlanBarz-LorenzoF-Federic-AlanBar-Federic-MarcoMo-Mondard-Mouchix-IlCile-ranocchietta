package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Startable;

public class Eagle extends MovingObjectImpl implements Startable<Integer> {
    private boolean started; 
    private int triggerRow;

    public Eagle(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(super.getDirection().equals(Direction.UP) ? "eagleUp.png" : "eagleDown.png");
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public void move() {
        if (started) {
            super.move();
        }
    }

    @Override
    public Integer getTrigger() {
        return this.triggerRow;    
    }

    @Override
    public void setTrigger(final Integer trigger) {
        this.triggerRow = trigger;
    }
}
