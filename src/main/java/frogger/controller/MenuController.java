package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface MenuController {

    public MouseMotionListener getMouseMotionListener();

    public MouseListener getMouseListener();
}