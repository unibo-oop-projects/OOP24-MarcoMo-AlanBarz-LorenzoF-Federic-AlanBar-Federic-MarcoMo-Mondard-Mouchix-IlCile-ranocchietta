package frogger.model.implementations;

import java.util.HashSet;
import java.util.Set;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;

public abstract class AbstractLaneImpl<T> implements Lane {

    public Class<T> type;
    public final Set<MovingObject> obstacles = new HashSet<>();
    private int speed;
    private Direction direction;

    public AbstractLaneImpl(int speed, Direction direction) {
        this.speed = speed;
        this.direction = direction;
    }

    abstract void setType();

    @Override
    public void addMovingObject(MovingObject obstacle) {
        setType();
        if (type.isInstance(obstacle)) {
            obstacles.add(obstacle);
        }

        throw new IllegalArgumentException("Wrong type of MovingObject.");
    }

    @Override
    public int getSpeed() {
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
