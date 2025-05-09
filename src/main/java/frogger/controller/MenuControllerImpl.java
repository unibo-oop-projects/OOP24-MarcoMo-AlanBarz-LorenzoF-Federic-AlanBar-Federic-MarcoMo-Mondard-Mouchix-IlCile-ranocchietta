package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.Menu;
import frogger.view.GameScene;
import frogger.view.MenuPanel;

public class MenuControllerImpl extends AbstractController implements MenuController{
 
    private MenuPanel scenePanel;
    private Menu menu;

    private final MouseInput mouseInput = new MouseInput(this);

    @Override
    public void init(GameScene gameScene) {
        menu = new Menu();
        scenePanel = new MenuPanel();
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
        return GameState.state == GameState.MENU;
    }

    @Override
    protected void changesLoopEnd() {}


    @Override
    public Menu getMenu() {
        return menu;
    }

    @Override
    public MouseMotionListener getMouseMotionListener() {
        return mouseInput;
    }

    @Override
    public MouseListener getMouseListener() {
        return mouseInput;
    }
}
