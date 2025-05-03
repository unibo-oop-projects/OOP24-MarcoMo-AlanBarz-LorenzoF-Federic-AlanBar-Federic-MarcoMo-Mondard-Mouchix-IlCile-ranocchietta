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

    // @Override
    // public void loop(){
    //     double timePerFrame = 10000000.0 / FPS_SET;
    //     long lastFrame = System.nanoTime();
    //     long now;

    //     while (GameState.state == GameState.MENU){
    //         now = System.nanoTime(); 
            
    //         if (now - lastFrame >= timePerFrame) {
    //             this.game.getMenu().update();
            
    //             this.scenePanel.repaint();
    //             lastFrame = now;
    //         }
    //     }
    // }

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
