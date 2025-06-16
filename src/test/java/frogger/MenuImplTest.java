package frogger;

import java.awt.Canvas;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.GameState;
import frogger.model.implementations.MenuImpl;
import frogger.model.interfaces.Button;

class MenuImplTest {

    private MenuImpl menu;

    @BeforeEach
    void setUp() {
        menu = new MenuImpl(GameState.PLAYING, GameState.SHOP, GameState.QUIT);
    }

    @Test
    void testButtonsAreCreated() {
        assertNotNull(menu.getButtonList());
        assertFalse(menu.getButtonList().isEmpty());
    }

    @Test
    void testButtonClickChangesGameState() {
        Button button = menu.getButtonList().get(0);
        GameState initialState = GameState.getState();
        button.applyGameState();
        assertEquals(GameState.PLAYING, GameState.getState());
        // Ripristina lo stato per non influenzare altri test
        GameState.setState(initialState);
    }

    @Test
    void testResetAllButtons() {
        for (Button button : menu.getButtonList()) {
            button.setMouseOver(true);
            button.setMousePressed(true);
        }

        /**
         * We reset all state of button, calling mouseReleased
         */
        menu.mouseReleased(new MouseEvent(
            new Canvas(), // source
            MouseEvent.MOUSE_RELEASED, // id
            System.currentTimeMillis(), // when
            0, // modifiers
            0, // x
            0, // y
            1, // clickCount
            false // popupTrigger
        ));

        for (Button button : menu.getButtonList()) {
            assertFalse(button.isMouseOver());
            assertFalse(button.isMousePressed());
        }
    }
}