package frogger.controller;

import frogger.common.GameState;
import frogger.view.GameScene;

public class MainControllerImpl {
    private GameScene frame;
    // private MenuPanel panel = new MenuPanel();
    private Controller controller;

    public void choosePanel() {
        frame = new GameScene();
        while (true) {
            switch (GameState.state) {
                case PLAYING -> {
                    controller = new GameControllerImpl();
                }
                case MENU -> {
                    controller = new MenuControllerImpl();
                }
                default -> System.exit(0);
            }
            
            controller.init(frame);
            controller.loop();
        }
    }
}
