
package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;

import frogger.controller.Controller;
import frogger.controller.GameControllerImpl;
import frogger.model.interfaces.PickableObjectManager;
import frogger.model.interfaces.PowerUp;

public class PickableObjectManagerImpl implements PickableObjectManager {
    private final List<PowerUp> activePowerUps = new ArrayList<>();
    private final GameImpl game;
    private Controller controller;

    public PickableObjectManagerImpl(GameImpl game) {
        this.game = game;
    }
    
    @Override
    public ArrayList<PowerUp> getActivePowerUps() {    
        // Filter out inactive power-ups
        activePowerUps.removeIf(powerUp -> !powerUp.isActive());   
        return new ArrayList<>(activePowerUps);
    }

    @Override
    public void addPickableObject(PickableObjectImpl x) {
        if (x != null) {
            if(x instanceof PowerUp timedPowerUp){
                activePowerUps.add(timedPowerUp);           
            }    
            
            switch (x.getRequiredDependencies()) {
                case PLAYER -> x.setRelatedEntity(game.getPlayer());
                case OBSTACLE -> x.setRelatedEntity(game.getObstacles());
                case GAME_CONTROLLER -> x.setRelatedEntity(controller);
                default -> {}
            }            

            x.onPick(); // Trigger the pick action
        }
    }

    public void setController(Controller controller) {
        if (controller instanceof GameControllerImpl gameController) {
            this.controller = gameController;
        }
    }
}
