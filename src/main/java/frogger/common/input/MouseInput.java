package frogger.common.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.common.GameState;
import frogger.controller.Controller;
import frogger.view.ScenePanel;


public class MouseInput implements MouseMotionListener, MouseListener {

    private final ScenePanel scenePanel;
    public MouseInput(ScenePanel scenePanel) {
        this.scenePanel = scenePanel;
    }

    // private final Controller controller;
    
    // public MouseInput(Controller controller) {
    //     this.controller = controller;
    // }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {           
            case SHOP -> {       
            // Handle shop event    
            }
            case DEAD -> {
            // Handle quit event
            }            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> {    
                scenePanel.getController()/*controller*/.getGame().getMenu().mousePressed(e);        
            }
            case SHOP -> {   
            // Handle shop event        
            }
            case DEAD -> {
            // Handle quit event
            }            
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> {    
                scenePanel.getController()/*controller*/.getGame().getMenu().mouseReleased(e);        
            }
            case SHOP -> {   
            // Handle shop event        
            }
            case DEAD -> {
            // Handle quit event
            }            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit event
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Handle mouse drag event
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> {    
            scenePanel.getController()/*controller*/.getGame().getMenu().mouseMoved(e);        
            }
            case SHOP -> {   
            // Handle shop event        
            }
            case DEAD -> {
            // Handle quit event
            }            
        }
    }
}