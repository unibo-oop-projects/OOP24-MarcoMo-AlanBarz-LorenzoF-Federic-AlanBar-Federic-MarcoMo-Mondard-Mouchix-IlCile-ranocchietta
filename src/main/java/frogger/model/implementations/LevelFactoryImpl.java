package frogger.model.implementations;

import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;

public class LevelFactoryImpl implements LevelFactory {

    private Level level;

    @Override
    public Level randomLevel() {
        level = new LevelImpl();

        Lane lane = new Road();
        lane.addMovingObject(new CarImpl(null, null, 0, null));
        level.addLane(lane);


        return level;
    }

}
