package frogger.model.interfaces;

import java.util.List;
import java.util.Set;

import frogger.model.implementations.Eagle;

public interface Level {

    List<Lane> getLanes();

    Set<MovingObject> getAllObstacles();

    void addLane(Lane lane);

    void addEagle(Eagle eagle);

    List<Eagle> getEagles();
}
