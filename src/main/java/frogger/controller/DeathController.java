package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.Menu;
import frogger.model.implementations.MenuFactory;
import frogger.view.GameScene;
import frogger.view.DeathPanel;

public class DeathController extends AbstractController implements MenuController{
    private DeathPanel scenePanel;
    private final MenuFactory menuFactory = new MenuFactory();
    private Menu menu;
    private final int score;

    private final MouseInput mouseInput = new MouseInput(this);

    public DeathController(int score) {
        this.score = score;
    }

    @Override
    public void init(GameScene gameScene) {
        menu = menuFactory.deathMenu();
        scenePanel = new DeathPanel(score);
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
    public Menu getMenu() {
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
