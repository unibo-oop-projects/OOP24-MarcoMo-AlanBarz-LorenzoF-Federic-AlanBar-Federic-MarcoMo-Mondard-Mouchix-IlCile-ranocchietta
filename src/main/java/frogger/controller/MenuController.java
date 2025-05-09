package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public interface MenuController<X> extends Controller {

    MouseMotionListener getMouseMotionListener();

    MouseListener getMouseListener();

    X getMenu();
}