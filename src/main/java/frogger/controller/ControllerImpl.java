package frogger.controller;

import java.util.Set;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.input.InputControllerImpl;
import frogger.model.implementations.CarImpl;
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
        game = new GameImpl(new PlayerObjectImpl(new Position(0, 0), new Pair(50, 50)));  //TODO: create the static class with the constant for dimention
        game.addObstacles(Set.of(new CarImpl(new Position(100,100), new Pair(100, 50), 0.1f, Direction.LEFT),
        new CarImpl(new Position(100,200), new Pair(100, 50), 0.1f, Direction.RIGHT),
        new CarImpl(new Position(100,300), new Pair(100, 50), 0.1f, Direction.LEFT),
        new CarImpl(new Position(100,400), new Pair(100, 50), 0.1f, Direction.RIGHT)));
    
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
                System.out.println("mosso l'ostacolo");
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
