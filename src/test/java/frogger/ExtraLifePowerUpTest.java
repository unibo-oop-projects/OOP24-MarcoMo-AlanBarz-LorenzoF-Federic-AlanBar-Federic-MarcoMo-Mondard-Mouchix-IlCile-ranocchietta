package frogger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.ExtraLifePowerUp;
import frogger.model.implementations.PickableObjectDependency;
import frogger.model.implementations.PlayerObjectImpl;

class ExtraLifePowerUpTest {
    private ExtraLifePowerUp powerUp;
    @SuppressWarnings("unused")
    private Position position;
    @SuppressWarnings("unused")
    private Pair dimension;

    @BeforeEach
    void setUp() {
        position = mock(Position.class);
        dimension = mock(Pair.class);
        //powerUp = new ExtraLifePowerUp(position, dimension, 0);
    }

    @Test
    void testApplyEffectAddsLifeToPlayer() {
        final PlayerObjectImpl player = mock(PlayerObjectImpl.class);
        // Simulate setting the related entity
        powerUp.setRelatedEntity(player);
        powerUp.applyEffect();
        verify(player, times(1)).addLife();
    }

    @Test
    void testApplyEffectDoesNothingIfNotPlayer() {
        final Object notAPlayer = mock(Object.class);
        powerUp.setRelatedEntity(notAPlayer);
        // Should not throw or call addLife
        assertDoesNotThrow(() -> powerUp.applyEffect());
    }

    @Test
    void testRemoveEffectDoesNothing() {
        // Should not throw
        assertDoesNotThrow(() -> powerUp.removeEffect());
    }

    @Test
    void testGetRequiredDependenciesReturnsPlayer() {
        assertEquals(PickableObjectDependency.PLAYER, powerUp.getRequiredDependencies());
    }
}
