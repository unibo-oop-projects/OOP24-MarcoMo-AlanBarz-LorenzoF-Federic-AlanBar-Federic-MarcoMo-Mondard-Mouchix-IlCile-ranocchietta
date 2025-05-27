package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PlayerObject;

public class FreezePowerUp extends  AbstractPowerUp {
    private static final int duration = 5000; // Duration in milliseconds
    private boolean active = false; // Indicates if the power-up is currently active
    private long startTime;

    public FreezePowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
        super.setImage("freezePowerUp.png");
    }
    
  

    @Override
    public boolean isActive() {
        if (active && System.currentTimeMillis() - startTime >= duration) {
            deactivate();
        }
        return active; 
    }
    
    @Override
    public void activate() {        
        active = true;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        this.active = false;
    }

    @Override
    public void setPlayer(PlayerObject player) {}

}
