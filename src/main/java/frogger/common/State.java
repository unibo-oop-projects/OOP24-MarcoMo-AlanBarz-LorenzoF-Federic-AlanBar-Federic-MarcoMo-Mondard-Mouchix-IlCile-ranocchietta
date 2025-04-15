package frogger.common;

import java.awt.event.MouseEvent;

import frogger.model.implementations.GameImpl;
import frogger.view.MenuButtons;

public class State {

    protected GameImpl game;
    public State(GameImpl game) {
        this.game = game;        
    }

    public boolean isIn(MouseEvent e, MenuButtons button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }
    public GameImpl getGame() {
        return game;
    }

}