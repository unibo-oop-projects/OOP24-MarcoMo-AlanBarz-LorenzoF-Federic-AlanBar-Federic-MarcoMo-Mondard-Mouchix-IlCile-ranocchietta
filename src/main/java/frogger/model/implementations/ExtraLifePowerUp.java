package frogger.model.implementations;

import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;

public class ExtraLifePowerUp extends InstantPowerUp {

    public ExtraLifePowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
        super.setImage(LoadSave.EXTRA_LIFE);
    }

    @Override
    public void applyEffect() {        
        if (relatedEntity instanceof PlayerObjectImpl player) {
            player.addLife();
        }
    }

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.PLAYER;
    }
    
}

    