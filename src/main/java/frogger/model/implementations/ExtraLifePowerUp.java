package frogger.model.implementations;

import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;

public class ExtraLifePowerUp extends PowerUpImpl {

    public ExtraLifePowerUp(Position pos, Pair dimension, int duration) {
        super(pos, dimension, duration); // Duration is not used for ExtraLifePowerUp
        super.setImage(LoadSave.EXTRA_LIFE);
    }

    @Override
    public void applyEffect() {        
        if (relatedEntity instanceof PlayerObjectImpl player) {
            player.addLife();
        }
    }

    @Override
    public void removeEffect() {}

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.PLAYER;
    }   
}

    