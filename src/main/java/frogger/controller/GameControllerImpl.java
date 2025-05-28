package frogger.controller;

import java.awt.event.KeyListener;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.Pair;
import frogger.common.input.InputController;
import frogger.common.input.InputControllerImpl;
import frogger.common.input.KeyInput;
import frogger.model.implementations.FreezePowerUp;
import frogger.model.implementations.GameImpl;
import frogger.model.implementations.PickableObjectManagerImpl;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.MovingObject;
import frogger.view.GamePanel;
import frogger.view.GameScene;

/**
 * Implementation of the {@link GameController} interface.
 * Manages the main game logic, player state, input, and game panel.
 */
public class GameControllerImpl extends AbstractController implements GameController {

    /** The game model instance. */
    private GameImpl game;
    /** The input controller for handling player commands. */
    private final InputControllerImpl inputController = new InputControllerImpl();
    /** The key listener for keyboard input. */
    private final KeyInput keyInput = new KeyInput(this);
    /** The panel displaying the game scene. */
    private GamePanel scenePanel;
    /** The number of coins the player currently has. */
    private int coins = 50;
    /** The identifier or path of the currently equipped skin. */
    private String skin = "ranocchietta.png";

    private PickableObjectManagerImpl pickableObjectManager;

    /**
     * {@inheritDoc}
     * Initializes the game scene and sets up the game panel.
     */
    @Override
    public void init(final GameScene gameScene) {
        scenePanel = new GamePanel();
        scenePanel.setController(this);
        gameScene.setPanel(scenePanel);
        pickableObjectManager = game.getPickableObjectManager();
        pickableObjectManager.setController(this);
    }

    /**
     * {@inheritDoc}
     * Executes the core game loop logic: processes input, checks collisions and progress,
     * updates obstacles, and repaints the scene.
     */
    @Override
    public void core() {
        this.inputController.processInput(this.game);
        this.game.checkCollision();
        this.game.checkProgress();
        this.game.checkNewLevel();
        this.game.checkEagleTrigger();
        if (!pickableObjectManager.getActivePowerUps().stream()
            .filter(powerUp -> powerUp instanceof FreezePowerUp)
            .anyMatch(powerUp -> ((FreezePowerUp) powerUp).isFrozen()))
        {
            this.game.getObstacles().forEach(MovingObject::move); // moving all obstacles
        }
        this.scenePanel.repaint();
    }

    /**
     * {@inheritDoc}
     * The loop continues as long as the game is not over and not paused.
     */
    @Override
    public boolean loopCondition() {
        return !game.isGameOver() && !game.gameIsPaused();
    }

    /**
     * {@inheritDoc}
     * Sets the game state to DEAD if the game is not paused at the end of the loop.
     */
    @Override
    public void changesLoopEnd() {
        if (!game.gameIsPaused()) {
            GameState.state = GameState.DEAD;
        }
    }

    /**
     * {@inheritDoc}
     * Returns the current game instance.
     */
    @Override
    public Game getGame() {
        return game;
    }

    /**
     * {@inheritDoc}
     * Returns the input controller responsible for handling player commands.
     */
    @Override
    public InputController getInputController() {
        return this.inputController;
    }

    /**
     * {@inheritDoc}
     * Returns the key listener for handling keyboard input.
     */
    @Override
    public KeyListener getKeyListener() {
        return keyInput;
    }

    /**
     * {@inheritDoc}
     * Starts a new game session, resetting all relevant state.
     */
    @Override
    public void newGame() {
        this.game = new GameImpl(new Pair(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT), this.skin);
    }

    /**
     * {@inheritDoc}
     * Returns the number of coins the player currently has.
     */
    @Override
    public int getCoins() {
        return this.coins;
    }

    /**
     * {@inheritDoc}
     * Sets the number of coins the player has.
     */
    @Override
    public void setCoins(final int coins) {
        this.coins = coins;
    }

    /**
     * {@inheritDoc}
     * Returns the identifier or path of the currently equipped skin.
     */
    @Override
    public String getSkin() {
        return skin;
    }

    /**
     * {@inheritDoc}
     * Sets the currently equipped skin.
     */
    @Override
    public void setSkin(final String skin) {
        this.skin = skin;
    }
}

