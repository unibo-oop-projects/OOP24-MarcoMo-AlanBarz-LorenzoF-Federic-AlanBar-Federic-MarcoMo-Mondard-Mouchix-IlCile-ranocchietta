package frogger.model.implementations;


import java.awt.image.BufferedImage;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

public abstract class AbstractPowerUp extends GameObjectImpl implements PowerUp {    
    BufferedImage img;

    public AbstractPowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
    }
}