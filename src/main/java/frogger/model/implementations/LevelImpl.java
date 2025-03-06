package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;

public class LevelImpl implements Level{

    private final static int NUM_OF_LANES = 13;

    private final List<Lane> lanes = new ArrayList<>(NUM_OF_LANES);

    @Override
    public List<Lane> getLanes() {
        return new ArrayList<>(lanes);
    }

    public Set<MovingObject> getAllObstacles() {
        Set<MovingObject> obstacles = new HashSet<>();
        lanes.forEach(lane -> obstacles.addAll(lane.getLaneObstacles()));
        return new HashSet<>(obstacles);
    }

    @Override
    public void addLane(Lane lane) {
        lanes.add(lane);
    }

}
