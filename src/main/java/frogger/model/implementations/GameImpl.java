package frogger.model.implementations;

import frogger.common.Pair;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.PlayerObject;

import java.util.Set;

public class GameImpl implements Game{

    private PlayerObjectImpl player;
    private final LevelFactoryImpl levelFactory = new LevelFactoryImpl();
    private final Level level;

    public GameImpl(Pair dimension){
        this.player = new PlayerObjectImpl(dimension);
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
    public void checkCollision() {
        if(this.player.getPos().y() > -6 && this.player.getPos().y() < 0){
            if(this.level.getAllObstacles().stream().filter(x -> x.getPos().y() == this.player.getPos().y()).anyMatch(x -> x.getHitBox().intersects(this.player.getHitBox()))){
                this.player.getHit();
            }
        }else if(this.player.getPos().y() > 0 && this.player.getPos().y() < 6){
            /*boolean trovato = false;
            for(var obstacle: this.level.getAllObstacles()){
                int radius = obstacle.getDimension().width();
                if(p.x() < obstacle.getPos().x() + radius && p.x() > obstacle.getPos().x() - radius && obstacle.getPos().y() == p.y()){
                    if(obstacle instanceof TrunkImpl){
                        ((TrunkImpl)obstacle).setFrog(this.player);
                    }
                    trovato = true;
                }
            }

            if(!trovato){
                this.player.getHit();
            }*/
        }
        
    }

    public Set<MovingObject> getObstacles() {
        return level.getAllObstacles();
    }

    public PlayerObject getPlayer(){
        return this.player;
    }
}