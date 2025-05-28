package frogger.model.interfaces;

import frogger.model.implementations.Menu;

public interface MenuFactory {
    /**
     * Creates the main menu with options to play, shop, or quit.
     * @return the Main Menu
     */
    Menu mainMenu();

    /**
     * Creates the pause menu with options to resume playing, return to the main menu, or quit.
     * @return the Pause Menu
     */
    Menu pauseMenu();

    /**
     * Creates the death menu with options to resume playing, return to the main menu, or quit.
     * @return the Death Menu
     */
    Menu deathMenu();

}