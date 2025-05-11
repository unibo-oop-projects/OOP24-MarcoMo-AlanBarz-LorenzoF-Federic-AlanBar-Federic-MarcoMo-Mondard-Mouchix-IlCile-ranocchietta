package frogger.model.implementations;

import frogger.common.GameState;

public class MenuFactory {
    public Menu MainMenu() {
        return new Menu(GameState.PLAYING, GameState.SHOP, GameState.QUIT);
    }

    public Menu PauseMenu() {
        return new Menu(/*GameState.PLAYING, GameState.SHOP, GameState.QUIT*/);
    }

    public Menu DeathMenu() {
        return new Menu(GameState.PLAYING, GameState.MENU, GameState.QUIT);
    }
}
