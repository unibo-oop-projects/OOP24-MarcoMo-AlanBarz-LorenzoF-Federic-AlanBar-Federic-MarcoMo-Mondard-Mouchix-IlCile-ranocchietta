package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;

public class Car extends MovingObjectImpl {
    
    public Car(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(findImg().toString());
    }

    private StringBuilder findImg() {
        StringBuilder result = new StringBuilder();
        if (super.getDimension().width() > 1) {
            result.append("truk");
        } else {
            result.append("car");
        }

        if (super.getDirection().equals(Direction.LEFT)) {
            result.append("Left");
        } else {
            result.append("Right");
        }
        result.append("png");
        return result;
    }
}
