package frogger.model.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

import frogger.common.Pair;
import frogger.common.Position;

public interface GameObject {

    Position getPos();

    void setPos(Position pos);

    Pair getDimension();

    public Rectangle getHitBox();

    public void drawHitBox(Graphics g, float x, float y);
}