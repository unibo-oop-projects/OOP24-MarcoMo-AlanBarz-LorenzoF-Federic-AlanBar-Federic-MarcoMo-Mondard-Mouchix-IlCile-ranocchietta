package frogger.model.implementations;


import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

public abstract class AbstractPowerUp extends GameObjectImpl implements PowerUp {    
    protected PlayerObjectImpl player;

    public AbstractPowerUp(Position pos, Pair dimension) {
        super(pos, dimension);
    }
    
    public void setPlayer(PlayerObjectImpl player) {
        if (player instanceof PlayerObjectImpl playerObjectImpl) {
            this.player = playerObjectImpl;
        } else {
            throw new IllegalArgumentException("Invalid player object type");
        }
    }    
}