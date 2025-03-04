package frogger.model.interfaces;

import frogger.common.Pair;
import frogger.common.Position;

public interface GameObject {

    Position getPos();

    void setPos(Position pos);

    Pair getDimension();

    void resetPosition();
}