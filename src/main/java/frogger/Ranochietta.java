package frogger;

import frogger.controller.MainControllerImpl;

public class Ranochietta {

    public static void main(String[] args) {
        // ControllerImpl controller = new ControllerImpl();
        // controller.gameInit();
        // controller.mainLoop();
        MainControllerImpl mainController = new MainControllerImpl();
        mainController.choosePanel();
    }
}