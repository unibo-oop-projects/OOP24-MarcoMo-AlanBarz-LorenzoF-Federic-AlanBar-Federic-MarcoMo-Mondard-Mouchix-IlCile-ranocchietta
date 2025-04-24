package frogger.controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.Pair;
import frogger.common.input.InputController;
import frogger.common.input.InputControllerImpl;
import frogger.common.input.KeyInput;
import frogger.common.input.MouseInput;
import frogger.model.implementations.GameImpl;
import frogger.view.GamePanel;
import frogger.view.GameScene;
import frogger.view.ScenePanel;

public class GameControllerImpl implements Controller{
    private final int FPS_SET = 120;

    private GameImpl game;
    private InputControllerImpl inputController;
    private GamePanel scenePanel;
    private GameScene gameScene;
    private KeyInput keyInput = new KeyInput(this);
    private MouseInput mouseInput = new MouseInput(this);

    public void init(GameScene gameScene) {
        game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));
    
        scenePanel = new GamePanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
        inputController = new InputControllerImpl();
    }

    public void loop(){
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while (!game.isGameOver()){
            now = System.nanoTime(); 
            this.inputController.processInput(this.game);
            // System.out.println(now -lastFrame + " = " + timePerFrame);
            if (now - lastFrame >= timePerFrame) {
                this.game.checkCollision();
                this.game.checkProgress();
                this.game.checkNewLevel();
                this.game.checkEagleTrigger();
                this.game.getObstacles().forEach(a -> a.move()); //moving all obstacles
            
                this.scenePanel.repaint();
                lastFrame = now;
            }
            
        }
    }

    public void setFrame(GameScene gameScene) {
        this.gameScene = gameScene; 
    }

    public GameImpl getGame() {
        return game;
    }

    public GamePanel getScenePanel(){
        return this.scenePanel;
    }

    /**
     * convert the x position of the logic grid, into the x position on the screen in pixel 
     * @param pos
     * @return
     */
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

    public InputController getInputController() {
        return this.inputController;
    }

    public KeyListener getKeyListener() {
        return keyInput;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseInput;
    }

    public MouseListener getMouseListener() {
        return mouseInput;
    }
}

