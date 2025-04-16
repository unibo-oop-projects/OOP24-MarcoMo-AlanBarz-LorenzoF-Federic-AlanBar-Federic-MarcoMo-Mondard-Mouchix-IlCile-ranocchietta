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
        if (e.getKeyCode() == 38){
            this.scenePanel.getController().getInputController().notifyCommand(new MoveUp());;
        } else if (e.getKeyCode() == 40){
            this.scenePanel.getController().getInputController().notifyCommand(new MoveDown());
        } else if (e.getKeyCode() == 39){
            this.scenePanel.getController().getInputController().notifyCommand(new MoveRight());
        } else if (e.getKeyCode() == 37){
            this.scenePanel.getController().getInputController().notifyCommand(new MoveLeft());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
