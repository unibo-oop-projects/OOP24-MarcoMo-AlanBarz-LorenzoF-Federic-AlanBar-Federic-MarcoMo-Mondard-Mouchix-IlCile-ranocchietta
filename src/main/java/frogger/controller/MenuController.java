package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.model.implementations.Menu;


public interface MenuController extends Controller {

    MouseMotionListener getMouseMotionListener();

    MouseListener getMouseListener();

    Menu getMenu();
}