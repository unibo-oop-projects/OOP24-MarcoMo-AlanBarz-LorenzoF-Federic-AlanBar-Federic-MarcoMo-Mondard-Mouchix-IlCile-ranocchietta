package frogger;

import frogger.controller.ControllerImpl;

public class Ranochietta {

    public static void main(String[] args) {
        ControllerImpl controller = new ControllerImpl();
        controller.gameInit();
        controller.mainLoop();
    }
}