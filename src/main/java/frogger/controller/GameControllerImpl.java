package frogger.controller;

import java.awt.event.KeyListener;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.Pair;
import frogger.common.input.InputController;
import frogger.common.input.InputControllerImpl;
import frogger.common.input.KeyInput;
import frogger.model.implementations.GameImpl;
import frogger.model.interfaces.Game;
import frogger.view.GamePanel;
import frogger.view.GameScene;

public class GameControllerImpl extends AbstractController implements GameController{

    private GameImpl game;
    private InputControllerImpl inputController;
    private GamePanel scenePanel;
    private KeyInput keyInput = new KeyInput(this);

    public void init(GameScene gameScene) {
        game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));
        scenePanel = new GamePanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
        inputController = new InputControllerImpl();
    }

    // public void loop(){
    //     double timePerFrame = 1000000000.0 / FPS_SET;
    //     long lastFrame = System.nanoTime();
    //     long now;

    //     while (!game.isGameOver()){
    //         now = System.nanoTime(); 
    //         this.inputController.processInput(this.game);
            
    //         if (now - lastFrame >= timePerFrame) {
    //             lastFrame = now;
    //         }
    //     }
    //     // GameState.state = GameState.DEAD;
    // }

    @Override
    public void core() {
        this.inputController.processInput(this.game);
        this.game.checkCollision();
        this.game.checkProgress();
        this.game.checkNewLevel();
        this.game.checkEagleTrigger();
        this.game.getObstacles().forEach(a -> a.move()); //moving all obstacles
        this.scenePanel.repaint();
    }

    @Override
    public boolean loopCondition() {
        return !game.isGameOver();
    }

    @Override
    public void changesLoopEnd() {
        GameState.state = GameState.DEAD;
    }

    @Override
    public Game getGame() {
        return game;
    }

    // public GamePanel getScenePanel(){
    //     return this.scenePanel;
    // }

    @Override
    public InputController getInputController() {
        return this.inputController;
    }

    @Override
    public KeyListener getKeyListener() {
        return keyInput;
    }
}

