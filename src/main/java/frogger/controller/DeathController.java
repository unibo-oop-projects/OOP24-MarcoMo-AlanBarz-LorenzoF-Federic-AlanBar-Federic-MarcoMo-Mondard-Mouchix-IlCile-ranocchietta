package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.DeathMenu;
import frogger.view.GameScene;
import frogger.view.DeathPanel;

public class DeathController extends AbstractController implements MenuController<DeathMenu>{
    private DeathPanel scenePanel;
    private DeathMenu menu;

    private final MouseInput mouseInput = new MouseInput(this);

    @Override
    public void init(GameScene gameScene) {
        menu = new DeathMenu();
        scenePanel = new DeathPanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel); 
        // scenePanel.requestFocusInWindow();
    }

    @Override
    protected void core() {
        this.getMenu().update();
        this.scenePanel.repaint();
    }

    @Override
    public DeathMenu getMenu() {
        return this.menu;
    }

    @Override
    protected boolean loopCondition() {
        return GameState.state == GameState.DEAD;
    }

    @Override
    protected void changesLoopEnd() {}

    @Override
    public MouseMotionListener getMouseMotionListener() {
        return this.mouseInput;
    }

    @Override
    public MouseListener getMouseListener() {
        return this.mouseInput;
    }

}
