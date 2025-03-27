package frogger.model.implementations;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.PlayerObject;

import java.util.Optional;
import java.util.Set;

public class GameImpl implements Game{

    private final LevelFactoryImpl levelFactory = new LevelFactoryImpl();
    private PlayerObjectImpl player;
    private Level level;

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
            // getObstacles().stream().forEach(a -> {
            //     if (a instanceof TrunkImpl) {
            //         ((TrunkImpl)a).removeFrog();
            //     }
            // });
            Optional<MovingObject> obstacle = this.level.getAllObstacles().stream().
                    filter(x -> x.getPos().y() == this.player.getPos().y()).
                    filter(x -> x.getHitBox().intersects(this.player.getHitBox())).
                    findFirst();
            
            if (obstacle.isEmpty()) {
                this.player.getHit();
                return;
            }

            if (obstacle.get() instanceof TrunkImpl){
                ((TrunkImpl)obstacle.get()).setFrog(this.player);
            }
        }
    }

    @Override
    public Set<MovingObject> getObstacles() {
        return level.getAllObstacles();
    }

    @Override
    public PlayerObject getPlayer(){
        return this.player;
    }

    @Override
    public void checkNewLevel() {
        if(this.player.getPos().y()==6){
            this.level = this.levelFactory.randomLevel();
            this.player.addPoints(Constants.POINT_LEVEL_COMPLETED);
            this.player.resetPosition();
        }
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public Lane getCurrentLane() {
        return level.getLanes().get((int)this.player.getPos().y() + Constants.MAX_Y);
    }

    @Override
    public void checkProgress() {
        if (!getCurrentLane().isCompleted()) {
            this.player.addPoints(Constants.POINT_PER_LANE);
            this.getCurrentLane().setCompleted();
        }
    }
}