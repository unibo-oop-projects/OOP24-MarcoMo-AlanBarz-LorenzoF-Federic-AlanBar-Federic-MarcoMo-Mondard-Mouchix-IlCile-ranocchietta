package frogger.model.implementations;

import frogger.model.interfaces.Car;
import frogger.model.interfaces.MovingObject;

public class Road extends AbstractLaneImpl {

    @Override
    public void addMovingObject(MovingObject obstacle) {
        if (obstacle instanceof Car) {
            obstacles.add(obstacle);
        }

        throw new IllegalArgumentException("MovingObject type is incopatible with Road.");
    }
    
}
