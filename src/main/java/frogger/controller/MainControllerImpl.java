package frogger.controller;

import frogger.common.GameState;
import frogger.view.GameScene;

public class MainControllerImpl {
    private GameScene frame;
    private Controller controller;
    private Controller gameController = new GameControllerImpl();

    public void choosePanel() {
        frame = new GameScene();
        while (true) {
            switch (GameState.state) {
                case PLAYING -> {
                    //controller = new GameControllerImpl();
                    controller = gameController;
                }
                case MENU -> {
                    controller = new MenuControllerImpl();
                }
                case SHOP -> {
                    controller = new ShopController();
                }
                case DEAD -> {
                    controller = new DeathController();
                    gameController = new GameControllerImpl();
                }
                case PAUSE -> {
                    controller = new PauseController();
                }
                default -> System.exit(0);
            }
            
            controller.init(frame);
            controller.loop();
        }
    }
}
