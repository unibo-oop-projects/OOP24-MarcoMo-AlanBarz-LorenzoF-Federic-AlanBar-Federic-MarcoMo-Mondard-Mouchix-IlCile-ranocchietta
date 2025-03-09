package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;

public class LevelImpl implements Level{

    protected final static int MAX_X = 6;
    protected final static int MIN_X = -7;
    protected final static int MAX_Y = 6;
    protected final static int MIN_Y = -6;
    protected final static int ROAD_LANES = 5;
    protected final static int RIVER_LANES = 5;

    private final List<Lane> lanes = new ArrayList<>();

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

    @Override
    public int getTotalLanes() {
        return ROAD_LANES + RIVER_LANES;
    }

}
