package frogger;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.model.implementations.Eagle;
import frogger.model.implementations.Ground;
import frogger.model.implementations.LevelFactoryImpl;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;

public class LevelFactoryTest {

    private LevelFactory fact;
    private Level level;

    @BeforeEach
    void setUp() {
        fact = new LevelFactoryImpl();
        level = fact.randomLevel();
    }

    @Test
    void laneTest() {
        assertEquals(level.getLanes().size(), Constants.ROAD_LANES + Constants.RIVER_LANES + Constants.GROUND_LANES);
    }

    @Test
    void obstaclesTest() {
        level.getLanes().forEach(lane -> {
            if (!(lane instanceof Ground)) {
                assertTrue(lane.getLaneObstacles().size() <= Constants.MAX_OBSTACLES_NUMBER 
                && lane.getLaneObstacles().size() >= Constants.MIN_OBSTACLES_NUMBER);
            }
        });
        assertTrue(level.getAllObstacles().stream().map(o -> o.getPos()).distinct().count() == level.getAllObstacles().size());
    }

    @Test
    void eagleTest() {
        assertTrue(level.getEagles().size() <= Constants.MAX_EAGLES_NUMBER && level.getEagles().size() >= Constants.MIN_EAGLES_NUMBER);
        level.getEagles().forEach(e -> assertTrue(e.getTrigger() != Constants.MAX_Y && e.getTrigger() != Constants.MIN_Y));
        assertTrue(level.getEagles().stream().map(Eagle::getPos).distinct().count() == level.getEagles().size());
    }

    @Test
    void powerUpTest() {
        assertTrue(level.getPowerUp().size() <= Constants.MAX_POWER_UP_NUMBER && level.getEagles().size() >= Constants.MIN_POWER_UP_NUMBER);
    }

}
