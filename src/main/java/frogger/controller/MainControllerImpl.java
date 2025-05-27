package frogger.controller;

import frogger.common.GameState;
import frogger.view.GameScene;

/**
 * Main controller implementation that manages the switching between different game panels
 * based on the current {@link GameState}.
 */
public class MainControllerImpl {
    /**
     * The current active controller.
     */
    private Controller controller;
    /**
     * The controller responsible for the game logic.
     */
    private final GameController gameController = new GameControllerImpl();
    /**
     * The controller responsible for the shop logic.
     */
    private final ShopController shopController = new ShopController(gameController);

    /**
     * Chooses and initializes the appropriate panel/controller based on the current game state.
     * This method contains the main loop of the application.
     * @inheritDoc
     */
    public void choosePanel() {
        final GameScene frame = new GameScene();
        while (true) {
            switch (GameState.state) {
                case PLAYING -> {
                    this.controller = this.gameController;
                }
                case MENU -> {
                    this.controller = new MenuControllerImpl();
                    this.gameController.newGame();
                }
                case SHOP -> {
                    this.controller = this.shopController;
                }
                case DEAD -> {
                    this.controller = new DeathController(((GameController) gameController).getGame().getScore());
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
