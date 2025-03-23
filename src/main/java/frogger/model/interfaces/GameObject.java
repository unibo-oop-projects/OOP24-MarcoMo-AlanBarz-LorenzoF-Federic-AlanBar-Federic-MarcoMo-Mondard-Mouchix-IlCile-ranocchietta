package frogger.model.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.InputStream;

import frogger.common.Pair;
import frogger.common.Position;

public interface GameObject {

    Position getPos();

    void setPos(Position pos);

    Pair getDimension();

    public Rectangle getHitBox();

    public void drawHitBox(Graphics g, float x, float y);

    public void render(Graphics g, int x, int y);

    public void setImage(InputStream is);
}