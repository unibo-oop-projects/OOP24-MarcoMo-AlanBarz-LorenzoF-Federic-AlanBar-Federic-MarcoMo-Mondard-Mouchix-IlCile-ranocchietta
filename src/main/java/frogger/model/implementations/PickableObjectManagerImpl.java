
package frogger.model.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frogger.controller.Controller;
import frogger.controller.GameControllerImpl;
import frogger.model.interfaces.PickableObjectManager;
import frogger.model.interfaces.PowerUp;

public class PickableObjectManagerImpl implements PickableObjectManager {

    private final Map<PowerUpType, PowerUp> activePowerUps = new HashMap<>();   
    private final GameImpl game;
    private Controller controller;

    public PickableObjectManagerImpl(GameImpl game) {
        this.game = game;
    }
    
    @Override
    public void checkPowerUps() {
        for (var entry : activePowerUps.entrySet()) {
            PowerUp powerUp = entry.getValue();
            if (powerUp != null && !powerUp.isActive()) {
                activePowerUps.remove(entry.getKey());
            }
        }
    }
    

    @Override
    public void addPickableObject(PickableObjectImpl x) {
        if (x != null) {

            switch (x.getRequiredDependencies()) {
                case PLAYER -> x.setRelatedEntity(game.getPlayer());
                case OBSTACLE -> x.setRelatedEntity(game.getObstacles());
                case GAME_CONTROLLER -> x.setRelatedEntity(controller);
                default -> throw new IllegalArgumentException("Unsupported dependency type: " + x.getRequiredDependencies()); 
            }     
            
            if (x instanceof PowerUp powerUp) {
                PowerUpType type = powerUp.getPowerUpType();
                PowerUp previous = activePowerUps.get(type);
                if (previous != null) {
                    previous.deactivate();
                }
                activePowerUps.put(type, powerUp);
                powerUp.onPick(); 
                updatefieldsPowerUp(activePowerUps.get(type), previous);
            } else {
                x.onPick();
            }            
        }
    }

    private void updatefieldsPowerUp(PowerUp currentPowerUp, PowerUp lastPowerUp) {
        if (currentPowerUp != null && lastPowerUp != null) {
            if (currentPowerUp instanceof FreezePowerUp cPowerUp && lastPowerUp instanceof FreezePowerUp lPowerUp) {
                cPowerUp.setEntitiesSpeed(lPowerUp.getEntitiesSpeed());
            }
        }
    }

    @Override
    public List<PowerUp> getActivePowerUps() {
        return List.copyOf(activePowerUps.values());
    }

    public void setController(Controller controller) {
        if (controller instanceof GameControllerImpl gameController) {
            this.controller = gameController;
        }
    }
}
