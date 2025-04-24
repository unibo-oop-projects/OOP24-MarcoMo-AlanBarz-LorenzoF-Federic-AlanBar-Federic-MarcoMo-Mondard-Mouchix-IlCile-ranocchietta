package frogger.common.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import frogger.controller.GameController;

public class KeyInput implements KeyListener{

    private final GameController controller;
    
    public KeyInput(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38 -> this.controller.getInputController().notifyCommand(new MoveUp());
            case 40 -> this.controller.getInputController().notifyCommand(new MoveDown());
            case 39 -> this.controller.getInputController().notifyCommand(new MoveRight());
            case 37 -> this.controller.getInputController().notifyCommand(new MoveLeft());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
