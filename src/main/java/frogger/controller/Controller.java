package frogger.controller;

import frogger.common.input.InputController;
import frogger.model.implementations.GameImpl;
import frogger.view.GameScene;

public interface Controller {
    
    void init(GameScene gameScene);

    void loop();

    void setFrame(GameScene gameScene);

    GameImpl getGame();

    InputController getInputController();
}
