package frogger.model.implementations;

import java.util.LinkedList;
import java.util.List;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.Car;
import frogger.model.interfaces.Lane;

public class RoadImpl extends GameObjectImpl implements Lane {
    
    public RoadImpl(Position pos, int dimension) {
        super(pos, dimension);
    }

    private final List<Car> cars = new LinkedList<>();

    @Override
    public void spawnMovingObjct(float speed, Direction direction) {
        cars.add(new CarImpl(this.getPos(), 0, speed, direction));  //TODO: take the date from a controller
    }

    public List<Car> getCars() {
        return cars;
    }

}
