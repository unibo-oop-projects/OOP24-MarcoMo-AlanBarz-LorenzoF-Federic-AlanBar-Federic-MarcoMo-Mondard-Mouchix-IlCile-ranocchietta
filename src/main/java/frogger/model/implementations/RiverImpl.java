package frogger.model.implementations;

import java.util.LinkedList;
import java.util.List;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Trunk;

public class RiverImpl extends GameObjectImpl implements Lane{
    private final List<Trunk> trunks = new LinkedList<>();
    
    public RiverImpl(Position pos, Pair dimension) {
        super(pos, dimension);
    }

    @Override
    public void spawnMovingObjct(float speed, Direction direction) {
        trunks.add(new TrunkImpl(this.getPos(), new Pair(50, 100), speed, direction));  //TODO: take the date from a controller
    }

    public List<Trunk> getTrunks() {
        return trunks;
    }

}
