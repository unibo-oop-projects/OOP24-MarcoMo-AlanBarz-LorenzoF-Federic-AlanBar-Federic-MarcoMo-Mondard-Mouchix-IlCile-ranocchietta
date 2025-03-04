package frogger.controller;

import javax.xml.stream.FactoryConfigurationError;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.GameImpl;
import frogger.model.implementations.PlayerObjectImpl;
import frogger.view.GameScene;
import frogger.view.ScenePanel;

public class ControllerImpl {

    private GameImpl game;

    public void gameInit() {
        ScenePanel scenePanel = new ScenePanel();
        GameScene gameScene = new GameScene(scenePanel);
        game = new GameImpl(new PlayerObjectImpl(new Position(0, 0), new Pair(50, 50)));  //TODO: create the static class with the constant for dimention
    }

    public void mainLoop(){
        long previousCycleStartTime = System.currentTimeMillis();
        if(!game.isGameOver()){
            long currentCycleStartTime = System.currentTimeMillis();
			long elapsed = currentCycleStartTime - previousCycleStartTime;




            previousCycleStartTime = currentCycleStartTime;
        }
    }

    public GameImpl getGame() {
        return game;
    }

    


}
