package frogger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.ExtraLifePowerUp;
import frogger.model.implementations.PickableObjectDependency;
import frogger.model.implementations.PlayerObjectImpl;
import frogger.model.implementations.PowerUpType;

class ExtraLifePowerUpTest {

    @Test
    void testApplyEffectAddsLifeToPlayer() {
        PlayerObjectImpl player = mock(PlayerObjectImpl.class);
        // Simulate setting the related entity
        powerUp.setRelatedEntity(player);

        // Applica l'effetto
        powerUp.applyEffect();

        // Verifica che le vite siano aumentate di 1
        assertEquals(initialLives + 1, player.getLives());
    }

    @Test
    void testApplyEffectDoesNothingIfNotPlayer() {
        Object notAPlayer = mock(Object.class);
        powerUp.setRelatedEntity(notAPlayer);

        // Non deve lanciare eccezioni
        assertDoesNotThrow(powerUp::applyEffect);
    }

    @Test
    void testRemoveEffectDoesNothing() {
        ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        // Non deve lanciare eccezioni
        assertDoesNotThrow(powerUp::removeEffect);
    }

    @Test
    void testGetRequiredDependencies() {
        ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        assertEquals(PickableObjectDependency.PLAYER, powerUp.getRequiredDependencies());
    }

    @Test
    void testGetPowerUpType() {
        ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        assertEquals(PowerUpType.EXTRA_LIFE, powerUp.getPowerUpType());
    }
}
