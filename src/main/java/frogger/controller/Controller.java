package frogger.controller;

import frogger.model.implementations.GameImpl;
import frogger.model.implementations.Menu;
import frogger.view.GameScene;

public interface Controller {
    
    void init(GameScene gameScene);

    void loop();

    void setFrame(GameScene gameScene);

    GameImpl getGame();

    Menu getMenu();

    double getXinPixel(double x);

    double getYinPixel(double x);
}
