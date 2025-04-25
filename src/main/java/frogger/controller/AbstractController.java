package frogger.controller;

import frogger.common.Constants;
import frogger.view.GameScene;

public abstract class AbstractController implements Controller{
    private final int FPS_SET = 120;
    
    GameScene gameScene;

    @Override
    public abstract void init(GameScene gameScene);

    protected abstract void core();

    protected abstract boolean loopCondition();

    protected abstract void changesLoopEnd();

    @Override
    public void loop() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while (loopCondition()){
            now = System.nanoTime(); 
            
            if (now - lastFrame >= timePerFrame) {
                core();
                lastFrame = now;
            }
        }
        changesLoopEnd();
    }

    @Override
    public void setFrame(GameScene gameScene) {
        this.gameScene = gameScene;    
    }

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
