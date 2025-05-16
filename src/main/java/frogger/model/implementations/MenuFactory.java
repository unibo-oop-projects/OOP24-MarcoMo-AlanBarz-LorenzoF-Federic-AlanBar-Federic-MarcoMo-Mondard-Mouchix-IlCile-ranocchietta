package frogger.model.implementations;

import frogger.common.GameState;

public class MenuFactory {
    public Menu mainMenu() {
        return new Menu(GameState.PLAYING, GameState.SHOP, GameState.QUIT);
    }

    public Menu pauseMenu() {
        return new Menu(GameState.PLAYING, GameState.MENU, GameState.QUIT);
    }

    public Menu deathMenu() {
        return new Menu(GameState.PLAYING, GameState.MENU, GameState.QUIT);
    }
}
