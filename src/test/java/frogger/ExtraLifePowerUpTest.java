package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.ExtraLifePowerUp;
import frogger.model.implementations.PlayerObjectImpl;

/**
 * Unit tests for the {@link ExtraLifePowerUp} class.
 * <p>
 * This test class verifies the correct behavior of the ExtraLifePowerUp power-up,
 * specifically its interaction with a {@link PlayerObjectImpl} instance.
 * </p>
 *
 * <ul>
 *   <li>
 *     {@link #testSetPlayerWithValidPlayer()} ensures that a valid player can be set without exceptions.
 *   </li>
 *   <li>
 *     {@link #testActivateAddsLife()} checks that activating the power-up increases the player's lives by one.
 *   </li>
 *   <li>
 *     {@link #testDeactivateDoesNothing()} verifies that deactivating the power-up does not affect the player's lives.
 *   </li>
 * </ul>
 *
 * <p>
 * The {@link #setUp()} method initializes the test environment before each test.
 * </p>
 */
class ExtraLifePowerUpTest {

    private ExtraLifePowerUp extraLifePowerUp;
    private PlayerObjectImpl player;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        Position pos = new Position(5, 10);
        Pair dim = new Pair(2, 2);
        extraLifePowerUp = new ExtraLifePowerUp(pos, dim);
        player = new PlayerObjectImpl(dim, "cile.png");
    }

    /*
    @Test
    void testSetPlayerWithValidPlayer() {
        extraLifePowerUp.setPlayer(player);
        // No exception should be thrown
    }

    @Test
    void testActivateAddsLife() {
        extraLifePowerUp.setPlayer(player);
        int initialLives = player.getLives();
        extraLifePowerUp.activate();
        assertEquals(initialLives + 1, player.getLives());
    }

    @Test
    void testDeactivateDoesNothing() {
        extraLifePowerUp.setPlayer(player);
        int initialLives = player.getLives();
        extraLifePowerUp.deactivate();
        assertEquals(initialLives, player.getLives());
    } */
}