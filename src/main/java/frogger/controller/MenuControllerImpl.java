package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.common.input.MouseInput;
import frogger.model.implementations.MenuFactoryImpl;
import frogger.model.interfaces.Menu;
import frogger.model.interfaces.MenuFactory;
import frogger.view.GameScene;
import frogger.view.MenuPanel;

public class MenuControllerImpl extends AbstractController implements MenuController {
    private final MenuFactory menuFactory = new MenuFactoryImpl();
    private MenuPanel scenePanel;
    private Menu menu;

    private final MouseInput mouseInput = new MouseInput(this);

    @Override
    public void init(final GameScene gameScene) {
        menu = menuFactory.mainMenu();
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
        return GameState.getState() == GameState.MENU;
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    @Override
    public MouseMotionListener getMouseMotionListener() {
        return this.mouseInput;
    }

    @Override
    public MouseListener getMouseListener() {
        return this.mouseInput;
    }
}
