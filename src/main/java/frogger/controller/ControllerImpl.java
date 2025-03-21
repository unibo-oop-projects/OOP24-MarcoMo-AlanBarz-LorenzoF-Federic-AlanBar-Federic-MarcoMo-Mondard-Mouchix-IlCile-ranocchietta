package frogger.controller;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.common.input.InputController;
import frogger.common.input.InputControllerImpl;
import frogger.model.implementations.GameImpl;
import frogger.view.GameScene;
import frogger.view.ScenePanel;

public class ControllerImpl {
    private final int FPS_SET = 120;

    private GameImpl game;
    InputControllerImpl inputController;
    ScenePanel scenePanel;
    GameScene gameScene;

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
        long now = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (!game.isGameOver()){
            now = System.nanoTime();

            this.inputController.processInput(this.game);
            
            if (now - lastFrame >= timePerFrame) {
                this.game.checkCollision();
                game.getObstacles().forEach(a -> a.move()); //moving all obstacles
                scenePanel.repaint();

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
    public int getXinPixel(int x) {
        int centerX = Constants.FRAME_WIDTH / 2;
        int ratioX = Constants.FRAME_WIDTH / Constants.N_COLUMN;    //number of pixel per column
        return Math.round(centerX + x * ratioX);
    }

    public int getYinPixel(int y) {
        int centerY = Constants.FRAME_HEIGHT / 2;
        int ratioY = Constants.FRAME_HEIGHT / Constants.N_ROW;  //number of pixel per row
        return Math.round(centerY - y * ratioY);
    }
}

