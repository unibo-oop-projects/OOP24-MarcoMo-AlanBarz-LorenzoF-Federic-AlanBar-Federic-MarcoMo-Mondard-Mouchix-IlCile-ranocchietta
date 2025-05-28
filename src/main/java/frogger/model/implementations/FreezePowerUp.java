package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;

public class FreezePowerUp extends  TimedPowerUp {

    private boolean freeze = false;

    public FreezePowerUp(Position pos, Pair dimension, int duration) {
        super(pos, dimension, duration);
        super.setImage("freezePowerup.png");
    }

    @Override
    public void applyEffect() {
        freeze = true;
    }

    @Override
    public void removeEffect() {
        freeze = false;
    }
    
    public boolean isFrozen() {
        return freeze;
    }

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return null;
    }
}
