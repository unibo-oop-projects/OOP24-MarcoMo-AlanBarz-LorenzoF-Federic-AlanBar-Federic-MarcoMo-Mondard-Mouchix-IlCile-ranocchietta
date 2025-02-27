package frogger.model;

import frogger.common.Position;

public abstract class GameObject {
    private Position pos;
    private final int dimension;

    public GameObject(Position pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getDimension() {
        return dimension;
    }
}
