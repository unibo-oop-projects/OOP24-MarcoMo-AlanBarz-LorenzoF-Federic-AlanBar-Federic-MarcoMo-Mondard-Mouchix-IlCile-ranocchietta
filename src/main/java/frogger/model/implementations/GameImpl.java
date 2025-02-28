package frogger.model.implementations;

import frogger.model.interfaces.Game;

public class GameImpl implements Game{

    private PlayerObjectImpl player;

    public GameImpl(PlayerObjectImpl player){
        this.player = player;
    }

    @Override
    public boolean isGameOver() {
        return this.player.getLives()==0;
    }

    @Override
    public int getScore() {
        return this.player.getScore();
    }

}
