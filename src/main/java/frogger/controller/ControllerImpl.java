package frogger.controller;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.input.InputController;
import frogger.common.input.InputControllerImpl;
import frogger.model.implementations.GameImpl;
import frogger.view.GameScene;
import frogger.view.ScenePanel;

public class ControllerImpl {

    private GameImpl game;
    InputControllerImpl inputController;
    ScenePanel scenePanel;
    GameScene gameScene;

    public void gameInit() {
        game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));  //TODO: create the static class with the constant for dimention
    
        scenePanel = new ScenePanel();
        gameScene = new GameScene(scenePanel);
        scenePanel.setController(this);
        inputController = new InputControllerImpl();
    }

    public void mainLoop(){
        long previousCycleStartTime = System.currentTimeMillis();
        while (!game.isGameOver()){
            long currentCycleStartTime = System.currentTimeMillis();
			long elapsed = currentCycleStartTime - previousCycleStartTime;
            this.inputController.processInput(this.game);
            
            this.game.checkCollision(new Position(getXinPixel((int)this.game.getPlayer().getPos().x()), this.game.getPlayer().getPos().y()));

            for (var obstacle : game.getObstacles()) {
                if (!obstacle.move()) {
                    game.restartObstacle(obstacle);
                }
            }
            
            scenePanel.repaint();

            previousCycleStartTime = currentCycleStartTime;
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

