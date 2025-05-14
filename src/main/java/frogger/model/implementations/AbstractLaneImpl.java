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
    private boolean completed;

    public AbstractLaneImpl(final float speed, final Direction direction) {
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Abstact method to be implemented, add an obstacle to the list of type Car
     * @param obstacle the obstacle to add
     */
    public abstract void addCar(MovingObject obstacle);
    /**
     * Abstact method to be implemented, add an obstacle to the list of type Trunk
     * @param obstacle the obstacle to add
     */
    public abstract void addTrunk(MovingObject obstacle);

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public float getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<MovingObject> getLaneObstacles() {
        return new HashSet<>(obstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompleted() {
        this.completed = true;
    }

}
