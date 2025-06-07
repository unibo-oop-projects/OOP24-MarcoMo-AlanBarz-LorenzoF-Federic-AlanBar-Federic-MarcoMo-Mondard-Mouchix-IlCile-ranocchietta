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
            player.setScoreMultiplier(2);
        }
    }
    
    @Override
    public void removeEffect() {
        if (relatedEntity instanceof PlayerObjectImpl player) {
            player.setScoreMultiplier(1); // Reset the score multiplier
        }
    }
    
    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.PLAYER;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.X2_SCORE;
    }
}
