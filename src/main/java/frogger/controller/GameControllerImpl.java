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
import frogger.model.interfaces.MovingObject;
import frogger.view.GamePanel;
import frogger.view.GameScene;

public class GameControllerImpl extends AbstractController implements GameController{
    private final GameImpl game;
    private final InputControllerImpl inputController = new InputControllerImpl();
    private final KeyInput keyInput = new KeyInput(this);
    private GamePanel scenePanel;

    public GameControllerImpl() {
        game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));
    }

    @Override
    public void init(final GameScene gameScene) {
        //game = new GameImpl(new Pair(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT));
        scenePanel = new GamePanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
    }

    @Override
    public void core() {
        if(!this.game.getPlayer().isDead()){
            this.inputController.processInput(this.game);
        }
        this.game.checkCollision();
        this.game.checkProgress();
        this.game.checkNewLevel();
        this.game.checkEagleTrigger();
        this.game.getObstacles().forEach(MovingObject::move); //moving all obstacles
        this.scenePanel.repaint();
    }

    @Override
    public boolean loopCondition() {
        return !game.isGameOver() && !game.gameIsPaused();
    }

    @Override
    public void changesLoopEnd() {
        if (!game.gameIsPaused()) {
            GameState.state = GameState.DEAD;
        }
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

