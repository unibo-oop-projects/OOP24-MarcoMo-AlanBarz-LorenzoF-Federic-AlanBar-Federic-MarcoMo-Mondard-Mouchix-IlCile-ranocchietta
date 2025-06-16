package frogger;

import java.awt.Rectangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import frogger.common.GameState;
import frogger.model.implementations.MenuButtons;

class MenuButtonsTest {

    @Test
    void testGettersAndBounds() {
        MenuButtons button = new MenuButtons(100, 200, 1, GameState.PLAYING);
        assertEquals(100, button.getXPos());
        assertEquals(200, button.getYPos());

        Rectangle bounds = button.getBounds();
        assertNotNull(bounds);
        assertEquals(100, bounds.x);
        assertEquals(200, bounds.y);

        // Check that getBounds returns a copy
        Rectangle bounds2 = button.getBounds();
        assertNotSame(bounds, bounds2);
    }

    @Test
    void testMouseStates() {
        MenuButtons button = new MenuButtons(0, 0, 0, GameState.PLAYING);

        button.setMouseOver(true);
        assertTrue(button.isMouseOver());

        button.setMousePressed(true);
        assertTrue(button.isMousePressed());

        button.resetBools();
        assertFalse(button.isMouseOver());
        assertFalse(button.isMousePressed());
    }

    @Test
    void testApplyGameState() {
        MenuButtons button = new MenuButtons(0, 0, 0, GameState.PAUSE);
        button.applyGameState();
        assertEquals(GameState.PAUSE, GameState.getState());
    }
}
