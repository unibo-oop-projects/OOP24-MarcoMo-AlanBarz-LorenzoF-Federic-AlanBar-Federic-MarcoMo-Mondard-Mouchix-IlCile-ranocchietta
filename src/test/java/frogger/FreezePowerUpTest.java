package frogger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.FreezePowerUp;
import frogger.model.implementations.MovingObjectImpl;
import frogger.model.implementations.PickableObjectDependency;
import frogger.model.implementations.PowerUpType;

public class FreezePowerUpTest {
    // Dummy MovingObjectImpl per i test
    static class DummyMovingObject extends MovingObjectImpl {
        private float speed;

        public DummyMovingObject(float speed) {
            super(new Position(0, 0), new Pair(1, 1), speed, Direction.LEFT);
            this.speed = speed;
        }

        @Override
        public float getSpeed() {
            return speed;
        }

        @Override
        public void setSpeed(float speed) {
            this.speed = speed;
        }
    }

    @Test
    void testApplyEffectSetsSpeedToZeroAndStoresOriginalSpeeds() {
        DummyMovingObject obj1 = new DummyMovingObject(2.5f);
        DummyMovingObject obj2 = new DummyMovingObject(1.0f);
        List<MovingObjectImpl> obstacles = new ArrayList<>();
        obstacles.add(obj1);
        obstacles.add(obj2);

        FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        freeze.setRelatedEntity(obstacles);

        freeze.applyEffect();

        assertEquals(0f, obj1.getSpeed());
        assertEquals(0f, obj2.getSpeed());

        float[] storedSpeeds = freeze.getEntitiesSpeed();
        assertArrayEquals(new float[]{2.5f, 1.0f}, storedSpeeds, 0.0001f);
    }

    @Test
    void testRemoveEffectRestoresOriginalSpeeds() {
        DummyMovingObject obj1 = new DummyMovingObject(3.0f);
        DummyMovingObject obj2 = new DummyMovingObject(4.0f);
        List<MovingObjectImpl> obstacles = new ArrayList<>();
        obstacles.add(obj1);
        obstacles.add(obj2);

        FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        freeze.setRelatedEntity(obstacles);

        freeze.applyEffect();
        freeze.removeEffect();

        assertEquals(3.0f, obj1.getSpeed());
        assertEquals(4.0f, obj2.getSpeed());
    }

    @Test
    void testGetRequiredDependencies() {
        FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        assertEquals(PickableObjectDependency.OBSTACLE, freeze.getRequiredDependencies());
    }

    @Test
    void testGetPowerUpType() {
        FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        assertEquals(PowerUpType.FREEZE, freeze.getPowerUpType());
    }

    @Test
    void testSetAndGetEntitiesSpeedDefensiveCopy() {
        FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        float[] speeds = {1.1f, 2.2f};
        freeze.setEntitiesSpeed(speeds);

        float[] returned = freeze.getEntitiesSpeed();
        assertArrayEquals(speeds, returned, 0.0001f);

        // Modifica l'array restituito e verifica che non cambi quello interno
        returned[0] = 99.9f;
        assertNotEquals(returned[0], freeze.getEntitiesSpeed()[0]);
    }
}

