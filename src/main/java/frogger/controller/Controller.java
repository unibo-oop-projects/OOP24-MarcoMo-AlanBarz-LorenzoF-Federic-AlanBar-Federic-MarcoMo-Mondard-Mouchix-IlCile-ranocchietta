package frogger.controller;

import frogger.view.GameScene;

public interface Controller {
    
    void init(GameScene gameScene);

    void loop();

    void setFrame(GameScene gameScene);

    double getXinPixel(double x);

    double getYinPixel(double x);
}
