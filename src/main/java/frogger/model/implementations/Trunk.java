package frogger.model.implementations;

import java.util.Optional;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Carrier;

public class Trunk extends MovingObjectImpl implements Carrier<PlayerObjectImpl> {
    private Optional<PlayerObjectImpl> frog = Optional.empty();

    public Trunk(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage("trunk.png");
    }

    @Override
    public void setObj(final PlayerObjectImpl frog) {
        this.frog = Optional.of(frog);
    }

    @Override
    public void removeObj() {
        this.frog = Optional.empty();
    }

    @Override
    public void move() {
        if (frog.isPresent()) {
            if (frog.get().getPos().y() != this.getPos().y()) {
                this.removeObj();
            } else {
                frog.get().setPos(new Position(frog.get().getPos().x() + this.getDirectionValue().x() * this.getSpeed(), 
                frog.get().getPos().y() + this.getDirectionValue().y() * this.getSpeed()));
            }
        }
        super.move();
    }

}
