package frogger.model;

import java.util.LinkedList;
import java.util.List;

import frogger.common.Position;

public class Road extends GameObject implements Lane {
    public Road(Position pos, int dimension) {
        super(pos, dimension);
    }

    private final List<Car> cars = new LinkedList<>();

    @Override
    public void spawnMovingObjct(float speed, int direction) {
        cars.add(new Car(this.getPos(), 0, speed, direction));  //TODO: take the date from a controller
    }

    public List<Car> getCars() {
        return cars;
    }

}
