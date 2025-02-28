package frogger.controller;

import frogger.model.implementations.GameImpl;

public class ControllerImpl {

    private GameImpl game;

    void mainLoop(){
        long previousCycleStartTime = System.currentTimeMillis();
        if(!game.isGameOver()){
            long currentCycleStartTime = System.currentTimeMillis();
			long elapsed = currentCycleStartTime - previousCycleStartTime;




            previousCycleStartTime = currentCycleStartTime;
        }
    }
}
