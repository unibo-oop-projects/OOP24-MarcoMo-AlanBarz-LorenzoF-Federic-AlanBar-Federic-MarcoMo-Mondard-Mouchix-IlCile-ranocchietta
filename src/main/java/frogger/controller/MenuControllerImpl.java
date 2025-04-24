package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.Pair;
import frogger.common.input.MouseInput;
import frogger.model.implementations.GameImpl;
import frogger.view.GameScene;
import frogger.view.MenuPanel;

public class MenuControllerImpl implements Controller{
private final int FPS_SET = 120;

    private GameImpl game;  
    private MenuPanel scenePanel;

    @SuppressWarnings("unused")
    private GameScene gameScene;

    private final MouseInput mouseInput = new MouseInput(this);

    @Override
    public void init(GameScene gameScene) {
        game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));
        this.gameScene = gameScene;
        scenePanel = new MenuPanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
    }

    @Override
    public void loop(){
        double timePerFrame = 10000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while (GameState.state == GameState.MENU){
            now = System.nanoTime(); 
            
            if (now - lastFrame >= timePerFrame) {
                this.game.getMenu().update();
            }
            this.scenePanel.repaint();
            lastFrame = now;
        }
    }

    @Override
    public void setFrame(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public GameImpl getGame() {
        return game;
    }

    public double getXinPixel(double x) {
        int centerX = Constants.FRAME_WIDTH / 2;
        int ratioX = Constants.BLOCK_WIDTH;    //number of pixel per column
        return Math.round(centerX + x * ratioX);
    }

    public double getYinPixel(double y) {
        int centerY = Constants.FRAME_HEIGHT / 2 - Constants.BLOCK_HEIGHT / 2;
        int ratioY = Constants.BLOCK_HEIGHT;  //number of pixel per row
        return Math.round(centerY - y * ratioY);
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseInput;
    }

    public MouseListener getMouseListener() {
        return mouseInput;
    }
}
