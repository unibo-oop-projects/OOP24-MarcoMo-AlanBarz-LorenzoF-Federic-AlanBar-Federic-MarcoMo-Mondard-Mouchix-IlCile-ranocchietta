package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;

public class LevelImpl implements Level{

    private final static int NUM_OF_LANES = 13;

    private final List<Lane> lanes = new ArrayList<>(NUM_OF_LANES);

    @Override
    public List<Lane> getLanes() {
        return new ArrayList<>(lanes);
    }

    /*@Override
    public void addMovingObjectInLane(MovingObject obstacle, int lane) {
        lanes.get(lane).addMovingObject(obstacle);
    }*/

    @Override
    public void addLane(Lane lane) {
        lanes.add(lane);
    }

}
