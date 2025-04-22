package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Startable;

public class Eagle extends MovingObjectImpl implements Startable<Integer> {
    private boolean started = false; 
    private int triggerRow = 0;

    public Eagle(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(this.getDirection().equals(Direction.UP) ? getClass().getResourceAsStream("/eagleUp.png")
        : getClass().getResourceAsStream("/eagleDown.png"));
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
    public void setTrigger(Integer trigger) {
        this.triggerRow = trigger;
    }
}
