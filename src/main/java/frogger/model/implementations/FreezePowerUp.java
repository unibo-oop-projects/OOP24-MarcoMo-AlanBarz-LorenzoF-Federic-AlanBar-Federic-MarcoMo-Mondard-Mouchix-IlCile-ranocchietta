package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PlayerObject;

public class FreezePowerUp extends  AbstractPowerUp {
    private static final int FREEZE_DURATION = 5000; // Duration in milliseconds
    private boolean isActive = false; // Indicates if the power-up is currently active
    private long freezeStartTime;

    public FreezePowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
        super.setImage("freezePowerUp.png");
    }
    
  
    public boolean update() {
        if (isActive && (System.currentTimeMillis() - freezeStartTime >= FREEZE_DURATION)) {
            deactivate();
            return true; // Power-up effect has ended
        }
        return false; // Power-up effect is still active
    }

    @Override
    public void activate() {        
        isActive = true;
        freezeStartTime = System.currentTimeMillis();
    }

    @Override
    public void deactivate() {
        this.isActive = false;
    }

    @Override
    public void setPlayer(PlayerObject player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPlayer'");
    }

}
