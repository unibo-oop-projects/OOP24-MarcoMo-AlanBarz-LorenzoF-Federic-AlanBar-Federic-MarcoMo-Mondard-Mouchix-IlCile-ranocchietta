package frogger.model.implementations;

import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;

public class ExtraLifePowerUp extends AbstractPowerUp {

    public ExtraLifePowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
        setImage();
    }

    @Override
    public void setImage() {
        img = LoadSave.GetSprite(LoadSave.EXTRA_LIFE); 
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deactivate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
