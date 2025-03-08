package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;

public class LevelFactoryImpl implements LevelFactory {

    private Level level;

    @Override
    public Level randomLevel() {
        level = new LevelImpl();
        Lane start = new Ground();
        level.addLane(start);
        for(int i = 0; i < 5; i++) {
            Lane road = new Road(1, Direction.RIGHT);
            level.addLane(road);
        }
        Lane mid = new Ground();
        level.addLane(mid);
        for(int i = 0; i < 5; i++) {
            Lane river = new River(1, Direction.RIGHT);
            level.addLane(river);
        }
        return level;
    }

    

}
