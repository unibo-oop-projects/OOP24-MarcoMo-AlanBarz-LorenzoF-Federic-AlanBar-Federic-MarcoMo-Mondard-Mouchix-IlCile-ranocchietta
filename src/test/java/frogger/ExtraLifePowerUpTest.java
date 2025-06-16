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
        // Crea un player reale
        final PlayerObjectImpl player = new PlayerObjectImpl(new Pair(1, 1), "skin.png");
        int initialLives = player.getLives();

        // Crea il power-up e assegnagli il player come relatedEntity
        ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        powerUp.setRelatedEntity(player);

        // Applica l'effetto
        powerUp.applyEffect();

        // Verifica che le vite siano aumentate di 1
        assertEquals(initialLives + 1, player.getLives());
    }

    @Test
    void testApplyEffectDoesNothingIfNotPlayer() {
        // Crea un oggetto che NON è un player
        final Object notAPlayer = new Object();

        ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
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
}
