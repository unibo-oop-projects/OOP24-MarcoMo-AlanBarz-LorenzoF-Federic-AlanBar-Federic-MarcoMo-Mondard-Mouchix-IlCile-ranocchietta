package frogger.model.implementations;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.GameObject;

public abstract class GameObjectImpl implements GameObject {
    private Position pos;
    private final Pair dimension;
    protected Rectangle2D.Float hitbox;
    private BufferedImage img;

    public GameObjectImpl(Position pos, Pair dimension) {
        this.pos = pos;
        this.dimension = dimension;
        
        initHitBox();
    }

    private void initHitBox() {
        this.hitbox = new Rectangle2D.Float(this.pos.x(), this.pos.y(), this.dimension.width(), this.dimension.height());
    }

    protected void updateHitBox(){
        this.hitbox.x = this.pos.x();
        this.hitbox.y = this.pos.y();
    }

    @Override
    public Rectangle2D.Float getHitBox(){
        return this.hitbox;
    }

    @Override
    public Position getPos() {
        return this.pos;
    }

    @Override
    public void setPos(Position pos) {
        this.pos = pos;
        updateHitBox();
    }

    @Override
    public Pair getDimension() {
        return dimension;
    }

    // @Override
    // public void render(Graphics g, int x, int y) {
    //     g.drawImage(img, x, y, this.getDimension().width() * Constants.BLOCK_WIDTH, 
    //     this.getDimension().height() * Constants.BLOCK_HEIGHT, null);
    // }
    
    @Override
    public BufferedImage getImage() {
        return img;
    }
    @Override
    public void setImage(String fileName) {
        img = LoadSave.GetSprite(fileName);
    }

}
