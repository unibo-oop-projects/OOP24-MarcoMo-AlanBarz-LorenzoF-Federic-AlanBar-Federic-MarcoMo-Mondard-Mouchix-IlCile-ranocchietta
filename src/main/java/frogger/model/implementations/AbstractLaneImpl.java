package frogger.model.implementations;

import java.util.HashSet;
import java.util.Set;

import frogger.common.Direction;
import frogger.model.interfaces.Car;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.Trunk;

public abstract class AbstractLaneImpl implements Lane {

    protected final Set<MovingObject> obstacles = new HashSet<>();
    private double speed;
    private Direction direction;

    public AbstractLaneImpl(double speed, Direction direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public abstract void addCar(MovingObject obstacle);
    public abstract void addTrunk(MovingObject obstacle);

    @Override
    public void addMovingObject(MovingObject obstacle) {
        if (obstacle instanceof Car) {
            addCar(obstacle);
        } else if (obstacle instanceof Trunk) {
            addTrunk(obstacle);
        } else {
            throw new IllegalArgumentException("Wrong type of MovingObject.");
        } 
    }

    @Override
    public double getSpeed() {
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

}
