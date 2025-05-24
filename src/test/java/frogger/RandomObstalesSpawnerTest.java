package frogger;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.model.implementations.Car;
import frogger.model.implementations.RandomObstaclesSpawner;
import frogger.model.interfaces.EntitySpawner;

public class RandomObstalesSpawnerTest {

    @Test
    void numberTest() {
        final float speed = Constants.MAX_SPEED;
        final Direction dir = Direction.RIGHT;
        
        int bound = Constants.MAX_OBSTACLES_NUMBER - Constants.MIN_OBSTACLES_NUMBER + 1;
        for (int i = 0; i < bound; i++) {
            final int value = i;
            final Random ran = new Random() {
                @Override
                public int nextInt(int bound) {
                    return value;
                }
            };
            EntitySpawner<Car> spawner = new RandomObstaclesSpawner<>(Car.class, 0, speed, dir, ran);
            List<Car> entity = spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER);
            assertTrue(entity.size() <= Constants.MAX_OBSTACLES_NUMBER 
            && entity.size() >= Constants.MIN_OBSTACLES_NUMBER);
        }
    }
}
