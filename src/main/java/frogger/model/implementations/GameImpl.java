package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Game;

import java.util.HashSet;
import java.util.Set;

public class GameImpl implements Game{

    private PlayerObjectImpl player;
    private Set<MovingObjectImpl> obstacles;

    public GameImpl(PlayerObjectImpl player){
        this.player = player;
        this.obstacles = new HashSet<>(Set.of(new CarImpl(new Position(100,100),new Pair(50,100), 1,Direction.LEFT)));
    }

    @Override
    public boolean isGameOver() {
        return this.player.getLives()==0;
    }

    @Override
    public int getScore() {
        return this.player.getScore();
    }

    @Override
    public boolean checkCollision() {
        return this.obstacles.stream().map(x -> x.getPos()).anyMatch(x -> x.equals(this.player.getPos()));
    }

    public Set<MovingObjectImpl> getObstacles() {
        return Set.copyOf(obstacles);
    }

    public PlayerObjectImpl getPlayer(){
        return this.player;
    }

}
