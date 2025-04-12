package frogger.model.interfaces;

import java.util.List;
import java.util.Set;

public interface Level {

    List<Lane> getLanes();

    Set<MovingObject> getAllObstacles();

    void addLane(Lane lane);

    void addEagle(MovingObject eagle);

    List<MovingObject> getEagles();
}
