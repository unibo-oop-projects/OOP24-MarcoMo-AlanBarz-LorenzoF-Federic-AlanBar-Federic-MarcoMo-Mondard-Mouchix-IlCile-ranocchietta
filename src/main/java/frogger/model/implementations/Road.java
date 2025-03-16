package frogger.model.implementations;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.Car;
import frogger.model.interfaces.MovingObject;

public class Road extends AbstractLaneImpl {

    public Road(float speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    public void addCar(MovingObject obstacle) {
        super.obstacles.add((Car)obstacle);
    }

    @Override
    public void addTrunk(MovingObject obstacle) {
        throw new IllegalStateException("Wrong type of obstacle.");
    }

    @Override
    public void restartObstacle(final MovingObject movingObject) {
        MovingObjectFactoryImpl factory = new MovingObjectFactoryImpl();
        this.addCar(factory.createMovingObject(
            new Position((movingObject.getDirection() == Direction.RIGHT? 0 : Constants.FRAME_WIDTH), movingObject.getPos().y()),
             movingObject.getDimension(), getSpeed(), getDirection(), CarImpl.class));
        super.obstacles.remove(movingObject);
    }
    
}
