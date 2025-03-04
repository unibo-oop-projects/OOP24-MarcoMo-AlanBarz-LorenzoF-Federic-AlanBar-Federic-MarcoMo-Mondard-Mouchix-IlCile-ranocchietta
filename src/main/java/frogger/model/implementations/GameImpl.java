package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameImpl implements Game, KeyListener{

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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38){
            this.player.setLookingUp();
            this.player.setPos(new Position(this.player.getPos().x(), this.player.getPos().y() + 1));
        } else if (e.getKeyCode() == 40){
            this.player.setLookingDown();
            this.player.setPos(new Position(this.player.getPos().x(), this.player.getPos().y() - 1));
        } else if (e.getKeyCode() == 39){
            this.player.setLookingRight();
            this.player.setPos(new Position(this.player.getPos().x() + 1, this.player.getPos().y()));
        } else if (e.getKeyCode() == 37){
            this.player.setLookingLeft();
            this.player.setPos(new Position(this.player.getPos().x() - 1, this.player.getPos().y()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public boolean checkCollision() {
        return this.obstacles.stream().map(x -> x.getPos()).anyMatch(x -> x.equals(this.player.getPos()));
    }

    public Set<MovingObjectImpl> getObstacles() {
        return Set.copyOf(obstacles);
    }

    

}
