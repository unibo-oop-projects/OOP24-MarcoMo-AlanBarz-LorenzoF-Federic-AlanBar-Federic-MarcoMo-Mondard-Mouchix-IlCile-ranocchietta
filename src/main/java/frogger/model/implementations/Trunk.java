package frogger.model.implementations;

import java.util.Optional;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Carrier;

public class Trunk extends MovingObjectImpl implements Carrier {

    private Optional<PlayerObjectImpl> frog = Optional.empty();

    public Trunk(Position pos, Pair dimension, float speed, Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(getClass().getResourceAsStream("/trunk.png"));
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
            if (frog.get().getPos().y() != this.getPos().y()) {
                this.removeFrog();
            } else {
                frog.get().setPos(new Position(frog.get().getPos().x() + this.getDirectionValue().x() * this.getSpeed(), 
                frog.get().getPos().y() + this.getDirectionValue().y() * this.getSpeed()));
            }
        }
        super.move();
    }

}
