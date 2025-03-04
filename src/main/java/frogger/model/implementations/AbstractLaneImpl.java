package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;

public abstract class AbstractLaneImpl implements Lane {

    public final List<MovingObject> obstacles = new ArrayList<>();
    private int speed;
    private Direction direction;

    @Override
    public abstract void addMovingObject(MovingObject obstacle);

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public List<MovingObject> getLaneObstacles() {
        return new ArrayList<>(obstacles);
    }

}
