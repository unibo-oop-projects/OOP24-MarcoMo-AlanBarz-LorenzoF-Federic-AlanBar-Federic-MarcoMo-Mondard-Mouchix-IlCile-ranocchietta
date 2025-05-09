package frogger.model.implementations;


import java.awt.image.BufferedImage;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

public abstract class AbstractPowerUp extends GameObjectImpl implements PowerUp {    
    protected  BufferedImage img;
    protected PlayerObjectImpl player;

    public AbstractPowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
    }
    
    public void setPlayer(PlayerObjectImpl player) {
        this.player = player;
    }    

    @Override
    public BufferedImage getImage(){
        return img;
    };
}