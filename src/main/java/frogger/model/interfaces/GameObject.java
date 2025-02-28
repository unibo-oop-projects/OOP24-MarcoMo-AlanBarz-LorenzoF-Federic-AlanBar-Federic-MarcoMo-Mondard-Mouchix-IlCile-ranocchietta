package frogger.model.interfaces;

import frogger.common.Position;

public interface GameObject {

    Position getPos();

    void setPos(Position pos);

    int getDimension();

    void resetPosition();
}