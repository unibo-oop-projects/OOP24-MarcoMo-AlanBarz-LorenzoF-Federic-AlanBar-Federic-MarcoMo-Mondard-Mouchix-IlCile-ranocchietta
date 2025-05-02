package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.PowerUp;

public class LevelImpl implements Level{

    private final List<Lane> lanes = new ArrayList<>();
    private final List<Eagle> eagles = new ArrayList<>();
    private final List<PowerUp> powerUp = new ArrayList<>();

    @Override
    public List<Lane> getLanes() {
        return new ArrayList<>(lanes);
    }

    @Override
    public List<MovingObject> getAllObstacles() {
        return Stream.concat(lanes.stream().flatMap(elem -> elem.getLaneObstacles().stream()), this.eagles.stream()).collect(Collectors.toList());
    }

    @Override
    public void addLane(Lane lane) {
        lanes.add(lane);
    }

    @Override
    public void addEagle(Eagle eagle) {
        this.eagles.add((Eagle)eagle);
    }

    @Override
    public List<Eagle> getEagles() {
        return new ArrayList<>(this.eagles);
    }

    @Override
    public List<PowerUp> getPowerUp() {
        return new ArrayList<>(this.powerUp);
    }

    @Override
    public void addPowerUp(PowerUp p) {
        this.powerUp.add(p);
    }
}
