package frogger.common.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import frogger.view.ScenePanel;

public class KeyInput implements KeyListener{

    private final ScenePanel scenePanel;
    
    public KeyInput(ScenePanel scenePanel) {
        this.scenePanel = scenePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38 -> this.scenePanel.getController().getInputController().notifyCommand(new MoveUp());
            case 40 -> this.scenePanel.getController().getInputController().notifyCommand(new MoveDown());
            case 39 -> this.scenePanel.getController().getInputController().notifyCommand(new MoveRight());
            case 37 -> this.scenePanel.getController().getInputController().notifyCommand(new MoveLeft());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
