package frogger.model.implementations;

import java.util.HashSet;
import java.util.Set;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;

public abstract class AbstractLaneImpl implements Lane {

    protected final Set<MovingObject> obstacles = new HashSet<>();
    private final float speed;
    private final Direction direction;
    private boolean completed = false;

    public AbstractLaneImpl(float speed, Direction direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public abstract void addCar(MovingObject obstacle);
    public abstract void addTrunk(MovingObject obstacle);

    @Override
    public void addMovingObject(final MovingObject obstacle) {
        if (obstacle instanceof Car) {
            addCar(obstacle);
        } else if (obstacle instanceof Trunk) {
            addTrunk(obstacle);
        } else {
            throw new IllegalArgumentException("Wrong type of MovingObject.");
        } 
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public Set<MovingObject> getLaneObstacles() {
        return new HashSet<>(obstacles);
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    @Override
    public void setCompleted() {
        this.completed = true;
    }

}
