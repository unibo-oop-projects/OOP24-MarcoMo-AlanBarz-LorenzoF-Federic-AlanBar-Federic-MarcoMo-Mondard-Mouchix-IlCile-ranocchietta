package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.Menu;
import frogger.model.implementations.MenuFactory;
import frogger.view.GameScene;
import frogger.view.PausePanel;

public class PauseController extends AbstractController implements MenuController {
    private final MenuFactory menuFactory = new MenuFactory();
    private PausePanel scenePanel;
    private Menu menu;

    private final MouseInput mouseInput = new MouseInput(this);

    @Override
    public void init(GameScene gameScene) {
        menu = menuFactory.PauseMenu();
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
    public Menu getMenu() {
        return this.menu;
    }

}
