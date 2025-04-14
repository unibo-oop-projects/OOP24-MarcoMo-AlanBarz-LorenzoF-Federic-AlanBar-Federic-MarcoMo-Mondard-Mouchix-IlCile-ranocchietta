package frogger.controller;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.Pair;
import frogger.common.input.InputController;
import frogger.common.input.InputControllerImpl;
import frogger.model.implementations.GameImpl;
import frogger.view.GameScene;
import frogger.view.ScenePanel;

public class ControllerImpl {
    private final int FPS_SET = 120;

    private GameImpl game;
    private InputControllerImpl inputController;
    private ScenePanel scenePanel;
    private GameScene gameScene;
    private GameState gameState;

    public void gameInit() {
        game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));
    
        scenePanel = new ScenePanel();
        gameScene = new GameScene(scenePanel);
        scenePanel.setController(this);
        inputController = new InputControllerImpl();
    }

    public void mainLoop(){
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while (!game.isGameOver()){
            now = System.nanoTime(); 
            this.inputController.processInput(this.game);

            if (now - lastFrame >= timePerFrame) {
                switch (GameState.state) {
                    case PLAYING -> {
                        this.game.checkCollision();
                        this.game.checkProgress();
                        this.game.checkNewLevel();
                        this.game.checkEagleTrigger();
                        this.game.getObstacles().forEach(a -> a.move()); //moving all obstacles
                        this.scenePanel.repaint();
                    }
                    case MENU -> {
                        //TODO: implement menu
                    }
                    case SHOP -> {
                        //TODO: implement menu
                    }
                    case DEAD -> {
                        //TODO: implement menu
                    }
                    default -> System.exit(0);
                }
           
            lastFrame = now;
            }            
        }
    }

    public GameImpl getGame() {
        return game;
    }

    public InputController getInputController() {
        return this.inputController;
    }

    public ScenePanel getScenePanel(){
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
}

