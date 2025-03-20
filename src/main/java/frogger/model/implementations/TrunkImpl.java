package frogger.model.implementations;

import java.util.Optional;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Trunk;

public class TrunkImpl extends MovingObjectImpl implements Trunk{

    private Optional<PlayerObjectImpl> frog = Optional.empty();

    public TrunkImpl(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
    }

    public void setFrog(PlayerObjectImpl frog) {
        this.frog = Optional.of(frog);
    }

    public void removeFrog() {
        this.frog = Optional.empty();
    }

    @Override
    public void move() {
        if (frog.isPresent()) {
            frog.get().setPos(new Position(frog.get().getPos().x() + (int)(this.getDirectionValue().x() * this.getSpeed()), 
            this.getPos().y() + (int)(this.getDirectionValue().y() * this.getSpeed())));
        }
        super.move();
    }

}
