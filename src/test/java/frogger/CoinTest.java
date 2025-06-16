package frogger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.controller.GameControllerImpl;
import frogger.model.implementations.Coin;
import frogger.model.implementations.PickableObjectDependency;

class CoinTest {

    private Position position;
    private Pair dimension;
    private Coin coin;

    @BeforeEach
    void setUp() {
        position = new Position(1, 2);
        dimension = new Pair(10, 10);
        coin = new Coin(position, dimension);
    }

    @Test
    void testGetRequiredDependenciesReturnsGameController() {
        assertEquals(PickableObjectDependency.GAME_CONTROLLER, coin.getRequiredDependencies());
    }

    @Test
    void testOnPickIncreasesCoinsByRandomValue() {
        GameControllerImpl controller = new GameControllerImpl();
        controller.setCoins(10);

        try {
            var field = coin.getClass().getSuperclass().getDeclaredField("relatedEntity");
            field.setAccessible(true);
            field.set(coin, controller);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            fail("Failed to set relatedEntity: " + e.getMessage());
        }

        coin.onPick();

        int coins = controller.getCoins();
        assertTrue(coins >= 11 && coins <= 15,
                "Coins after pick: " + coins);
    }

    @Test
    void testOnPickDoesNothingIfRelatedEntityIsNotGameController() {
        Object unrelatedEntity = new Object();
        try {
            var field = coin.getClass().getSuperclass().getDeclaredField("relatedEntity");
            field.setAccessible(true);
            field.set(coin, unrelatedEntity);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            fail("Failed to set relatedEntity: " + e.getMessage());
        }

        assertDoesNotThrow(() -> coin.onPick());
    }
}