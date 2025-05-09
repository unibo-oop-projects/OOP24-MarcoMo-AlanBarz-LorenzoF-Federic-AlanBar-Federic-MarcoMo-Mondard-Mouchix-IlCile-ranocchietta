package frogger.model.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Button {

    void draw(Graphics g);

    void update();

    boolean isMouseOver();

    void setMouseOver(boolean mouseOver);

    boolean isMousePressed();

    void setMousePressed(boolean mousePressed);

    Rectangle getBounds();

    void applyGameState();

    void resetBools();

}