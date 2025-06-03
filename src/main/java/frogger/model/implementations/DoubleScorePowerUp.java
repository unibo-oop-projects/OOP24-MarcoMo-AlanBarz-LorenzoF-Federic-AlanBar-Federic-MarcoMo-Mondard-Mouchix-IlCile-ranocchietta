package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;

public class DoubleScorePowerUp extends PowerUpImpl {

    public DoubleScorePowerUp(final Position pos, final Pair dimension, final int duration) {
        super(pos, dimension, duration);
        super.setImage("x2_PawerUp.png");
    }
    
    @Override
    public void applyEffect() {
        if (relatedEntity instanceof PlayerObjectImpl player) {
            player.addPoints(player.getScore());
        }
    }
    
    @Override
    public void removeEffect() {}
    
    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.PLAYER;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.X2_SCORE;
    }
}
