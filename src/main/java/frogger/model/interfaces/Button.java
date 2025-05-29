package frogger.model.interfaces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface Button {

    void update();

    BufferedImage getCurrentImg();

    int getXPos();

    int getYPos();

    boolean isMouseOver();

    void setMouseOver(boolean mouseOver);

    boolean isMousePressed();

    void setMousePressed(boolean mousePressed);

    Rectangle getBounds();

    void applyGameState();

    void resetBools();

}