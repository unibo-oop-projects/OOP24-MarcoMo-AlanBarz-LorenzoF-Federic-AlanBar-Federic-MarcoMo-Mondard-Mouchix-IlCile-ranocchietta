package frogger.model.implementations;

import java.util.Set;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.PlayerObject;

public class GameImpl implements Game{

    private final LevelFactoryImpl levelFactory = new LevelFactoryImpl();
    private PlayerObjectImpl player;
    private Level level;
    private final Menu menu;

    public GameImpl(Pair dimension){
        this.menu = new Menu(this);
        this.player = new PlayerObjectImpl(dimension);
        level = levelFactory.randomLevel();
    }

    public Menu getMenu() {
        return this.menu;
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
        
        if(this.player.getPos().y() > -6 && this.player.getPos().y() < 1){
            if(this.level.getAllObstacles().stream().anyMatch(x -> x.getHitBox().intersects(this.player.getHitBox()))){
                this.player.getHit();
            }
        }else if(this.player.getPos().y() > 0 && this.player.getPos().y() < 6){

            this.player.setAttached(false);
            
            if(this.level.getAllObstacles().stream().anyMatch(x -> x.getHitBox().intersects(this.player.getHitBox()))){
                getObstacles().stream().filter(x -> x.getHitBox().intersects(this.player.getHitBox())).forEach(x ->{
                    if(x instanceof Trunk){
                        if(!this.player.isAttached()){
                            ((Trunk)x).setObj(this.player);
                            this.player.setAttached(true);
                        }
                    } else if(x instanceof Eagle){
                        this.player.getHit();
                    }
                });
            } else {
               this.player.getHit(); 
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

    public void checkEagleTrigger() {
        for (var eagle : this.level.getEagles()) {
            if(eagle.getTrigger() == this.getPlayer().getPos().y()) { 
                eagle.start();
            }
        }
    }
}