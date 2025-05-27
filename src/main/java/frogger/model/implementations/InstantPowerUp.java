package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;

public abstract class InstantPowerUp extends PickableObjectImpl {

    public InstantPowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
    }

    @Override
    public void onPick() {
        applyEffect();
    }

    abstract public void applyEffect(); 
}
