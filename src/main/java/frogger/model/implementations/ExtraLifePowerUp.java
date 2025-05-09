package frogger.model.implementations;

import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PlayerObject;

public class ExtraLifePowerUp extends AbstractPowerUp {

    public ExtraLifePowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
    }

    @Override
    public void setImage() {
        img = LoadSave.GetSprite(LoadSave.EXTRA_LIFE); 
    }

    @Override
    public void activate() {
        player.addLive();
    }
    
    @Override
    public void deactivate() {}

    @Override
    public void setPlayer(PlayerObject player) {      
        if (player instanceof PlayerObjectImpl playerObjectImpl) {
            this.player = playerObjectImpl;
        } else {
            throw new IllegalArgumentException("Invalid player object type");
        }
    }
}
    