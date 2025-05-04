package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.Car;
import frogger.model.implementations.Ground;
import frogger.model.implementations.MovingObjectFactoryImpl;
import frogger.model.implementations.River;
import frogger.model.implementations.Road;
import frogger.model.implementations.Trunk;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;

public class LaneTest {
    private MovingObject ob1;
    private MovingObject ob2;
    private float speed;
    private Direction dir;

    @BeforeEach
    void setUp() {
        float speed = Constants.MIN_SPEED;
        Direction dir = Direction.LEFT;
        Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        Position pos = new Position(0, 0);
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();

        ob1 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Trunk.class);
        ob2 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Car.class);
    }

    @Test
    void riverTest() {
        Lane l1 = new River(speed, dir);
        try {
            l1.addMovingObject(ob2);
            Assertions.fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(Set.of(), l1.getLaneObstacles());
        }
        l1.addMovingObject(ob1);
        assertEquals(Set.of(ob1), l1.getLaneObstacles());
    }

    @Test
    void roadTest() {
        Lane l1 = new Road(speed, dir);
        try {
            l1.addMovingObject(ob1);
            Assertions.fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(Set.of(), l1.getLaneObstacles());
        }
        l1.addMovingObject(ob2);
        assertEquals(Set.of(ob2), l1.getLaneObstacles());
    }

    @Test
    void groundTest() {
        Lane l1 = new Ground();
        try {
            l1.addMovingObject(ob1);
            Assertions.fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(Set.of(), l1.getLaneObstacles());
        }
        try {
            l1.addMovingObject(ob2);
            Assertions.fail("Should have thrown an exception.");
        } catch (Exception e) {
            assertEquals(Set.of(), l1.getLaneObstacles());
        }
    }
}
