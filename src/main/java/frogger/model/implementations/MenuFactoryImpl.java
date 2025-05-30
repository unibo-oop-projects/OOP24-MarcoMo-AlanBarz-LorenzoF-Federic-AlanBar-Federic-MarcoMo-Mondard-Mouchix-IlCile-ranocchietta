package frogger.model.implementations;

import frogger.common.GameState;
import frogger.model.interfaces.Menu;
import frogger.model.interfaces.MenuFactory;

public class MenuFactoryImpl implements MenuFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Menu mainMenu() {
        return new MenuImpl(GameState.PLAYING, GameState.SHOP, GameState.QUIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Menu pauseMenu() {
        return new MenuImpl(GameState.PLAYING, GameState.MENU, GameState.QUIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Menu deathMenu() {
        return this.pauseMenu();
    }
}
