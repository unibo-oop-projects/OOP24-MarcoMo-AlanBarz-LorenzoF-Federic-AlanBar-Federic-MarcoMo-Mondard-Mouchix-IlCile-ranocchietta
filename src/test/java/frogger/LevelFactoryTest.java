package frogger;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.model.implementations.Ground;
import frogger.model.implementations.LevelFactoryImpl;
import frogger.model.implementations.River;
import frogger.model.implementations.Road;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;

/**
 * Test class for LevelFactoryImpl.
 */
final class LevelFactoryTest {

    private Level level;

    @BeforeEach
    void setUp() {
        final LevelFactory fact = new LevelFactoryImpl();
        level = fact.randomLevel();
    }

    @Test
    void testRandomLevelNotNull() {
        assertNotNull(level, "The level should not be null.");
    }

    @Test
    void laneTest() {
        assertEquals(level.getLanes().size(), Constants.ROAD_LANES + Constants.RIVER_LANES
        + Constants.GROUND_LANES);

        assertTrue(level.getLanes().get(0) instanceof Ground,
        "The first lane should be Ground.");
        assertTrue(level.getLanes().get(Constants.ROAD_LANES + 1) instanceof Ground,
        "The mid lane should be Ground.");
        assertTrue(level.getLanes().get(level.getLanes().size() - 1) instanceof Ground,
        "The last lane should be Ground.");

        for (int i = 1; i <= Constants.ROAD_LANES; i++) {
            assertTrue(level.getLanes().get(i) instanceof Road,
            "Lanes from 1 to Road_Lanes should be Road.");
        }
        for (int i = Constants.ROAD_LANES + 2; i < level.getLanes().size() - 1; i++) {
            assertTrue(level.getLanes().get(i) instanceof River,
            "Lanes from Road_Lanes + 2 to the penultimate should be River.");
        }
    }

    @Test
    void obstaclesTest() {
        level.getLanes().forEach(lane -> {
            if (!(lane instanceof Ground)) {
                assertTrue(lane.getLaneObstacles().size() <= Constants.MAX_OBSTACLES_NUMBER 
                && lane.getLaneObstacles().size() >= Constants.MIN_OBSTACLES_NUMBER);
            }
        });
    }

    @Test
    void eagleTest() {
        assertTrue(level.getEagles().size() <= Constants.MAX_EAGLES_NUMBER
        && level.getEagles().size() >= Constants.MIN_EAGLES_NUMBER);
    }

    @Test
    void powerUpTest() {
        assertTrue(level.getPowerUp().size() <= Constants.MAX_POWER_UP_NUMBER
        && level.getPowerUp().size() >= Constants.MIN_POWER_UP_NUMBER);
    }

}
