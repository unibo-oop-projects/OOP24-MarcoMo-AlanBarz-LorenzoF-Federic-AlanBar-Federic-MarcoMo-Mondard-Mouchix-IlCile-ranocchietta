package frogger.controller;

import frogger.common.GameState;
import frogger.view.GameScene;

public class MainControllerImpl {
    private Controller controller;
    private GameController gameController = new GameControllerImpl();
    private ShopController shopController = new ShopController(gameController);

    public void choosePanel() {
        GameScene frame = new GameScene();
        while (true) {
            switch (GameState.state) {
                case PLAYING -> {
                    this.controller = this.gameController;
                    if(this.controller instanceof GameController) {
                        System.out.println(((GameController)controller).getSkin());
                    }
                }
                case MENU -> {
                    this.controller = new MenuControllerImpl();
                    this.gameController.newGame();
                }
                case SHOP -> {
                    this.controller = this.shopController;
                }
                case DEAD -> {
                    this.controller = new DeathController(((GameController)gameController).getGame().getScore());
                    this.gameController.newGame();
                }
                case PAUSE -> {
                    this.controller = new PauseController(gameController);
                }
                default -> System.exit(0);
            }
            
            this.controller.init(frame);
            this.controller.loop();
        }
    }
}
