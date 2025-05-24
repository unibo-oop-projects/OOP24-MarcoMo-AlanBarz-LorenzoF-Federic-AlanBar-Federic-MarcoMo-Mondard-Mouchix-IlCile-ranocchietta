package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.implementations.RandomPowerUpsSpawner;
import frogger.model.interfaces.EntitySpawner;
import frogger.model.interfaces.PowerUp;

public class RandomPowerUpsSpawnerTest {

    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
    }
    
    @Test
    void numberTest() {
        final int bound = Constants.MAX_POWER_UP_NUMBER - Constants.MIN_POWER_UP_NUMBER + 1;
        List<PowerUp> entity;
        
        //just checking if the number is right, so the position is always valid
        EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(bound)).thenReturn(0);  
        entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        assertEquals(entity.size(), Constants.MIN_POWER_UP_NUMBER);

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(bound)).thenReturn(bound - 1);
        entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        assertEquals(entity.size(), Constants.MAX_OBSTACLES_NUMBER);
    }

    @Test
    void generatePositionTest() {
        final int boundX = Constants.MAX_X - Constants.MIN_X + 1;
        final int boundY = Constants.MAX_Y - Constants.MIN_Y + 1;

        //Just checking if the position generated is correct, so is always valid
        EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check the x coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundX)).thenReturn(0);
        spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER)
        .forEach(e -> assertTrue(e.getPos().x() == Constants.MIN_X));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundX)).thenReturn(boundX - 1);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertTrue(e.getPos().x() == Constants.MAX_X));

        //Check the y coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundY)).thenReturn(0);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertTrue(e.getPos().y() == Constants.MIN_Y));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundY)).thenReturn(boundY - 1);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertTrue(e.getPos().y() == Constants.MAX_Y));
    }

    @Test
    void validPositionOverlapTest() {
        List<PowerUp> entity;
       
        //Checking if it works with a scenario where there is no overlap
        EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            private int i = 0;
            @Override
            protected Position generatePosition() {
                super.generatePosition();
                return new Position(i, 0);
            }
        };

        entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);

        final Set<Position> occupied = new HashSet<>();
        entity.forEach(e -> occupied.add(e.getPos()));
        
        assertEquals(occupied.size(), entity.size());

        //Checking if it works with a scenario where there is an overlap
        EntitySpawner<PowerUp> spawner1 = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                return new Position(0, 0);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it will go in time-out
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            spawner1.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        });
    }

    @Test
    void validPositionRowTest() {

        //Checking the first row
        EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                return new Position(0, Constants.MIN_Y);
            }
        };

        //In case of an invalid row the method should cycle until the overlap it's fixed,
        //but since the position is fixed it will go in time-out
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        });

        //Checking the last row
        EntitySpawner<PowerUp> spawner1 = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                super.generatePosition();
                return new Position(0, Constants.MAX_Y);
            }
        };

        //In case of an invalid row the method should cycle until the overlap it's fixed,
        //but since the position is fixed it will go in time-out
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            spawner1.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        });
    }
}
