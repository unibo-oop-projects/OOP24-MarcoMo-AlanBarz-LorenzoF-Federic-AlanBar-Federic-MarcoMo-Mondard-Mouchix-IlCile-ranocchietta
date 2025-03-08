package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.Car;

public class Road extends AbstractLaneImpl<Car> {

    public Road(int speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    void setType() {
        super.type = Car.class;
    }
    
}
