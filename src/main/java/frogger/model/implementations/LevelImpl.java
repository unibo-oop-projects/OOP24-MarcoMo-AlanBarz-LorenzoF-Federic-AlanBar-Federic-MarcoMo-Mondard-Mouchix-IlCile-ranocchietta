package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;

public class LevelImpl implements Level{

    private final List<Lane> lanes = new ArrayList<>();

    @Override
    public List<Lane> getLanes() {
        return new ArrayList<>(lanes);
    }

    @Override
    public Set<MovingObject> getAllObstacles() {
        return lanes.stream().flatMap(elem -> elem.getLaneObstacles().stream()).collect(Collectors.toSet());
    }

    @Override
    public void addLane(Lane lane) {
        lanes.add(lane);
    }
}
