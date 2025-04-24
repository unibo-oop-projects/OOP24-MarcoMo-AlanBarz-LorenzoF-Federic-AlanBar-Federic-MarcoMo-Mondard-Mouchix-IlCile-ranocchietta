package frogger.controller;

import frogger.common.input.InputController;

public interface GameController extends Controller {
    public InputController getInputController();
}
