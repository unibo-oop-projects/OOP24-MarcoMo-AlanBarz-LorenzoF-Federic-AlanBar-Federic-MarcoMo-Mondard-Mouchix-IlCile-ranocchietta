package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.PauseMenu;
import frogger.view.GameScene;
import frogger.view.PausePanel;

public class PauseController extends AbstractController implements MenuController<PauseMenu> {

    private PausePanel scenePanel;
    private PauseMenu menu;

    private final MouseInput mouseInput = new MouseInput(this);

    @Override
    public void init(GameScene gameScene) {
        menu = new PauseMenu();
        scenePanel = new PausePanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
    }

    @Override
    protected void core() {
        this.getMenu().update();
        this.scenePanel.repaint();
    }

    @Override
    protected boolean loopCondition() {
        return GameState.state == GameState.PAUSE;
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

    @Override
    public PauseMenu getMenu() {
        return this.menu;
    }

}
