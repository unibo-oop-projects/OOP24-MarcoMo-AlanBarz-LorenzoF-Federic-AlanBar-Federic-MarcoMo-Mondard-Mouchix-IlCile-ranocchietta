package frogger.model.implementations;

import frogger.common.Pair;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;

import java.util.HashSet;
import java.util.Set;

public class GameImpl implements Game{

    private PlayerObjectImpl player;
    private Set<MovingObjectImpl> obstacles;
    private final LevelFactoryImpl levelFactory = new LevelFactoryImpl();
    private final Level level;

    public GameImpl(Pair dimension){
        this.player = new PlayerObjectImpl(dimension);
        this.obstacles = new HashSet<>();
        level = levelFactory.randomLevel();
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

    public Set<MovingObject> getObstacles() {
        return level.getAllObstacles();
    }

    public PlayerObjectImpl getPlayer(){
        return this.player;
    }
}