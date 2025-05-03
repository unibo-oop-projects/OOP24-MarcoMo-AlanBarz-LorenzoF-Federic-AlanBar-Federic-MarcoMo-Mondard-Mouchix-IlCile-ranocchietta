package frogger.model.interfaces;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import frogger.common.Pair;
import frogger.common.Position;

public interface GameObject {

    Position getPos();

    void setPos(Position pos);

    Pair getDimension();

    public Rectangle2D.Float getHitBox();

    // public void render(Graphics g, int x, int y);

    public BufferedImage getImage();

    public void setImage(String fileName);
}