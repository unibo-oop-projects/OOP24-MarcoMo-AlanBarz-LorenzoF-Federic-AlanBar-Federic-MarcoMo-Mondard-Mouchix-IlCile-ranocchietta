
package frogger.model.implementations;

import java.util.ArrayList;
import java.util.List;

import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.PickableObjectManager;
import frogger.model.interfaces.PowerUp;

public class PickableObjectManagerImpl implements PickableObjectManager {
    private final List<TimedPowerUp> activePowerUps = new ArrayList<>();
    PlayerObjectImpl player;
    List<MovingObject> obstacles; 
    
    public PickableObjectManagerImpl() {}
    public PickableObjectManagerImpl(PlayerObjectImpl player, List<MovingObject> obstacles) {
        this.player = player;
        this.obstacles = obstacles;
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
            if(x instanceof  TimedPowerUp timedPowerUp){
                activePowerUps.add(timedPowerUp);           
            }    
            
            switch (x.getRequiredDependencies()) {
                case PLAYER -> x.setRelatedEntity(this.player);
                case OBSTACLE -> x.setRelatedEntity(this.obstacles);
            }            

            x.onPick(); // Trigger the pick action
        }
    }
}
