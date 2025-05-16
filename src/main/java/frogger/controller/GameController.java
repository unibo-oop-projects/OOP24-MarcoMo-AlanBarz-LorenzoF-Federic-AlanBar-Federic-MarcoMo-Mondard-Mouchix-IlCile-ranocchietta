package frogger.controller;

import java.awt.event.KeyListener;

import frogger.common.input.InputController;
import frogger.model.interfaces.Game;

public interface GameController extends Controller {
    
    InputController getInputController();

    KeyListener getKeyListener();

    Game getGame();

    int getCoins();

    void setCoins(int coins);

    void newGame();

    String getSkin();

    void setSkin(String skin);
}
