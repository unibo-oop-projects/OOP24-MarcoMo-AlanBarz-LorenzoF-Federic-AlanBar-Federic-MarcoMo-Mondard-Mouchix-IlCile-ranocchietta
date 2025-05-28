package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

public abstract class InstantPowerUp extends PickableObjectImpl implements PowerUp {

    public InstantPowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
    }

    @Override
    public void onPick() {
       activate();    
    }

    @Override
    public void activate() {
        applyEffect();
    }

    @Override
    public void deactivate() { }

    @Override
    public boolean isActive() {
        return false;
    }

    abstract public void applyEffect(); 
}
