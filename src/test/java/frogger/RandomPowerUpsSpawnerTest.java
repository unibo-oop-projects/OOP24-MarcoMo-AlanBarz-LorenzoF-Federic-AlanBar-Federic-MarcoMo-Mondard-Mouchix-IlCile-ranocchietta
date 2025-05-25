package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

/**
 * Test class for RandomPowerUps.
 */
public class RandomPowerUpsSpawnerTest {

    private Random mockRandom;
    private int bound;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        bound = Constants.MAX_POWER_UP_NUMBER - Constants.MIN_POWER_UP_NUMBER + 1;
    }
    
    @Test
    void numberTest() {
        List<PowerUp> entity;
        
        //just checking if the number is right, so the position is always valid
        final EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
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
        assertEquals(entity.size(), Constants.MAX_POWER_UP_NUMBER);
    }

    @Test
    void generatePositionTest() {
        final int boundX = Constants.MAX_X - Constants.MIN_X + 1;
        final int boundY = Constants.MAX_Y - Constants.MIN_Y + 1;

        //Just checking if the position generated is correct, so is always valid
        final EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check the x coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundX)).thenReturn(0);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
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
        final EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            private final int i = 0;
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

        //forcing the max number of power up, if it was just one there wouldn't be an overlap
        when(mockRandom.nextInt(bound)).thenReturn(Constants.MAX_POWER_UP_NUMBER);

        //Checking if it works with a scenario where there is an overlap
        final EntitySpawner<PowerUp> spawner1 = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                return new Position(0, 0);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        assertEquals(List.of(), spawner1.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER));
    }

    @Test
    void validPositionRowTest() {
        //forcing the max number of power up, if it was just one there wouldn't be an overlap
        when(mockRandom.nextInt(bound)).thenReturn(Constants.MAX_POWER_UP_NUMBER);

        //Checking the first row
        final EntitySpawner<PowerUp> spawner = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                return new Position(0, Constants.MIN_Y);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        assertEquals(List.of(), spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER));

        //Checking the last row
        final EntitySpawner<PowerUp> spawner1 = new RandomPowerUpsSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                return new Position(0, Constants.MAX_Y);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        assertEquals(List.of(), spawner1.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER));
    }
}
