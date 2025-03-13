package frogger.controller;

import frogger.common.Costants;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.input.InputControllerImpl;
import frogger.model.implementations.GameImpl;
import frogger.model.implementations.PlayerObjectImpl;
import frogger.view.GameScene;
import frogger.view.ScenePanel;

public class ControllerImpl {

    private GameImpl game;
    InputControllerImpl inputController = new InputControllerImpl();
    ScenePanel scenePanel;
    GameScene gameScene;

    public void gameInit() {
        game = new GameImpl(new PlayerObjectImpl(new Position(0, 0), new Pair(Costants.PLAYER_WIDTH,Costants.PLAYER_HEIGHT)));  //TODO: create the static class with the constant for dimention
    
        scenePanel = new ScenePanel();
        gameScene = new GameScene(scenePanel);
        scenePanel.setController(this);
    }

    public void mainLoop(){
        long previousCycleStartTime = System.currentTimeMillis();
        while (!game.isGameOver()){
            long currentCycleStartTime = System.currentTimeMillis();
			long elapsed = currentCycleStartTime - previousCycleStartTime;
            inputController.processInput(this.game);
            
            for (var elem : game.getObstacles()) {
                elem.move();
            }
            
            scenePanel.repaint();

            previousCycleStartTime = currentCycleStartTime;
        }
    }

    public GameImpl getGame() {
        return game;
    }
}
