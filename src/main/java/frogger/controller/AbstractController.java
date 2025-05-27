package frogger.controller;

import frogger.common.Constants;
import frogger.view.GameScene;

/**
 * An abstract base class for all controllers that need a loop.
 * Provides a generic game loop and utility methods for converting game coordinates
 * to pixel values. Subclasses must implement game-specific logic.
 */
public abstract class AbstractController implements Controller{
    /** The target frames per second for the game loop.*/
    private final static int FPS_SET = 120;
    /** One second in nanoseconds, used to calculate frame duration.*/
    private final static double SET = 1000000000.0;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void init(GameScene gameScene);

    /**
     * Contains the core logic of the game loop to be executed each frame.
     * Must be implemented by subclasses.
     */
    protected abstract void core();

    /**
     * Condition to keep the loop running.
     * When this returns false, the game loop will terminate.
     *
     * @return true to continue the loop, false to stop
     */
    protected abstract boolean loopCondition();

    /**
     * Defines what happens after the game loop ends.
     * Must be implemented by subclasses.
     */
    protected abstract void changesLoopEnd();

    /**
     * {@inheritDoc}
     */
    @Override
    public void loop() {
        final double timePerFrame = SET / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while (loopCondition()) {
            now = System.nanoTime(); 

            if (now - lastFrame >= timePerFrame) {
                core();
                lastFrame = now;
            }
        }
        changesLoopEnd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrame(final GameScene gameScene) {    
    }

    /**
     * Converts a logical x-coordinate (in game units) to a pixel value on screen.
     *
     * @param x the logical x-coordinate
     * @return the corresponding x-coordinate in pixels
     */
    public double getXinPixel(final double x) {
        final int centerX = Constants.FRAME_WIDTH / 2;
        final int ratioX = Constants.BLOCK_WIDTH;    //number of pixel per column
        return Math.round(centerX + x * ratioX);
    }

    /**
     * Converts a logical y-coordinate (in game units) to a pixel value on screen.
     *
     * @param y the logical y-coordinate
     * @return the corresponding y-coordinate in pixels
     */
    public double getYinPixel(final double y) {
        final int centerY = Constants.FRAME_HEIGHT / 2 - Constants.BLOCK_HEIGHT / 2;
        final int ratioY = Constants.BLOCK_HEIGHT;  //number of pixel per row
        return Math.round(centerY - y * ratioY);
    }

}
