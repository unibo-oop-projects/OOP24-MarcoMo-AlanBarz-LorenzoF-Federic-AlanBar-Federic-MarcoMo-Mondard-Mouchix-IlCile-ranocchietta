package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.Eagle;
import frogger.model.implementations.LevelImpl;
import frogger.model.implementations.MovingObjectFactoryImpl;
import frogger.model.implementations.PowerUpFactory;
import frogger.model.implementations.River;
import frogger.model.implementations.Road;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObjectFactory;
import frogger.model.interfaces.PowerUp;

public class LevelTest {

    private Level level;

    @BeforeEach
    void setUp() {
        level = new LevelImpl();
    }

    @Test
    void laneTest() {
        float speed = Constants.MIN_SPEED;
        Direction dir = Direction.DOWN;

        Lane l1 = new Road(speed, dir);
        Lane l2 = new Road(speed, dir);
        Lane l3 = new River(speed, dir);
        Lane l4 = new River(speed, dir);

        level.addLane(l1);
        level.addLane(l2);
        level.addLane(l3);
        level.addLane(l4);
        assertEquals(List.of(l1,l2,l3,l4), level.getLanes());
    }

    @Test
    void powerUpTest() {
        Pair dim = new Pair(Constants.POWER_UP_WIDTH, Constants.POWER_UP_HEIGHT);

        PowerUp p1 = PowerUpFactory.createPowerUp(PowerUpFactory.getRandomPowerUpType(), new Position(0, 0), dim);
        PowerUp p2 = PowerUpFactory.createPowerUp(PowerUpFactory.getRandomPowerUpType(), new Position(3, 4), dim);
        PowerUp p3 = PowerUpFactory.createPowerUp(PowerUpFactory.getRandomPowerUpType(), new Position(-1, 3), dim);
        PowerUp p4 = PowerUpFactory.createPowerUp(PowerUpFactory.getRandomPowerUpType(), new Position(-3, 1), dim);

        level.addPowerUp(p1);
        level.addPowerUp(p2);
        level.addPowerUp(p3);
        level.addPowerUp(p4);
        assertEquals(List.of(p1,p2,p3,p4), level.getPowerUp());
    }

    @Test
    void eagleTest() {
        Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        Position pos = new Position(0, 0);
        float speed = Constants.MIN_SPEED;
        Direction dir = Direction.DOWN;

        Eagle e1 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        Eagle e2 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        Eagle e3 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        Eagle e4 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);

        level.addEagle(e1);
        level.addEagle(e2);
        level.addEagle(e3);
        level.addEagle(e4);
        assertEquals(List.of(e1,e2,e3,e4), level.getEagles());
        assertEquals(List.of(e1,e2,e3,e4), level.getAllObstacles());
    }
}
