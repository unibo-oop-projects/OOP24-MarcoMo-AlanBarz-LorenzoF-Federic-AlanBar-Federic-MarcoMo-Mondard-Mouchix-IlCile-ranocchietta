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
            Position pos = new Position(frog.get().getPos().x() + (this.getDirectionValue().x() * this.getSpeed()), 
        this.getPos().y() + (this.getDirectionValue().y() * this.getSpeed()));
        System.out.println("rana: " + pos + " ostacolo: " + this.getPos());
            frog.get().setPos(new Position(frog.get().getPos().x() + (this.getDirectionValue().x() * this.getSpeed()), 
            this.getPos().y() + (this.getDirectionValue().y() * this.getSpeed())));
        }
        super.move();
    }

}
