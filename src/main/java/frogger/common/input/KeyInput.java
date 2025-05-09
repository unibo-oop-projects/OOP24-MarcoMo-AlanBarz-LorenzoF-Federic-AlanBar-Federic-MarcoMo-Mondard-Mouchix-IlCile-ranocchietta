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
            case KeyEvent.VK_UP -> this.controller.getInputController().notifyCommand(new MoveUp());
            case KeyEvent.VK_DOWN -> this.controller.getInputController().notifyCommand(new MoveDown());
            case KeyEvent.VK_RIGHT -> this.controller.getInputController().notifyCommand(new MoveRight());
            case KeyEvent.VK_LEFT -> this.controller.getInputController().notifyCommand(new MoveLeft());
            case KeyEvent.VK_ESCAPE -> this.controller.getInputController().notifyCommand(new Pause());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
