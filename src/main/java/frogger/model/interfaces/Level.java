package frogger.model.interfaces;

import java.util.List;

public interface Level {

    List<Lane> getLanes();

    //void addMovingObjectInLane(MovingObject obstacle, int lane);

    void addLane(Lane lane);
}
