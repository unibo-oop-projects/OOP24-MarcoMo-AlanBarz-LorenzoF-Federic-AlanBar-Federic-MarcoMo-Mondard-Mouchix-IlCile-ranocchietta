package frogger.controller;

import frogger.common.GameState;
import frogger.view.GameScene;

public class MainControllerImpl {
    private GameScene frame;
    private Controller controller;
    private String skin;
    private int coins;
    private Controller gameController = new GameControllerImpl();

    public void choosePanel() {
        frame = new GameScene();
        while (true) {
            switch (GameState.state) {
                case PLAYING -> {
                    controller = gameController;
                }
                case MENU -> {
                    controller = new MenuControllerImpl();
                    gameController = new GameControllerImpl();
                }
                case SHOP -> {
                    controller = new ShopController(this);
                }
                case DEAD -> {
                    controller = new DeathController();
                    gameController = new GameControllerImpl();
                }
                case PAUSE -> {
                    controller = new PauseController(gameController);
                }
                default -> System.exit(0);
            }
            
            controller.init(frame);
            controller.loop();
        }
    }

    public void setSkin(String image) {
        this.skin = image;
    }

    public String getSkin() {
        return this.skin;
    }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int i) {
        this.coins = i;
    }
}
