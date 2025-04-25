package frogger.controller;

import java.awt.event.KeyListener;

import frogger.common.input.InputController;

public interface GameController {
    public InputController getInputController();

    public KeyListener getKeyListener();
}
