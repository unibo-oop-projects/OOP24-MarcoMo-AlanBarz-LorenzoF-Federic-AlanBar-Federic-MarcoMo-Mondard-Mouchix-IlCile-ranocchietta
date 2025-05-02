package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;

public class Car extends MovingObjectImpl {
    
    public Car(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);

        super.setImage(findImg());
        
    }

    private String findImg() {
        String result = "";
        if (this.getDimension().width() > 1) {
            result += "truk";
        } else {
            result += "car";
        }

        if (this.getDirection().equals(Direction.LEFT)) {
            result += "Left";
        } else {
            result += "Right";
        }
        result += ".png";
        return result;
    }
}
