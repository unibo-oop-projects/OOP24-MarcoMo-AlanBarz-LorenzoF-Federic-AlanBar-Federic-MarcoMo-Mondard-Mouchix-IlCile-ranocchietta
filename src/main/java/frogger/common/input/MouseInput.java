package frogger.common.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

import frogger.common.GameState;
import frogger.controller.AbstractController;
import frogger.controller.DeathController;
import frogger.controller.MenuController;
import frogger.controller.MenuControllerImpl;
import frogger.model.implementations.Menu;


public class MouseInput implements MouseMotionListener, MouseListener {
    private Optional<MenuControllerImpl> menuController = Optional.empty();
    private Optional<DeathController> deathController = Optional.empty();
    
    public <X extends AbstractController> MouseInput(X controller) {
        switch (GameState.state) {
            case MENU -> {    
                this.menuController = Optional.of((MenuControllerImpl)controller);       
            }
            case SHOP -> {   
            // Handle shop event        
                this.menuController = Optional.of((MenuControllerImpl)controller);
            }
            case DEAD -> {
            // Handle quit event
                this.deathController = Optional.of((DeathController)controller);
            }     
            default -> {
                this.menuController = Optional.of((MenuControllerImpl)controller);
                // Handle default case
            }       
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {           
            case SHOP -> {       
            // Handle shop event    
            }
            case DEAD -> {
            // Handle quit event
            } 
            default -> {
                // Handle default case
            }           
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> {    
                /*scenePanel.getController()*/menuController.get().getMenu().mousePressed(e);        
            }
            case SHOP -> {   
            // Handle shop event        
            }
            case DEAD -> {
                deathController.get().getMenu().mousePressed(e);
            }     
            default -> {
                // Handle default case
            }       
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> {    
                /*scenePanel.getController()*/menuController.get().getMenu().mouseReleased(e);        
            }
            case SHOP -> {   
            // Handle shop event        
            }
            case DEAD -> {
                deathController.get().getMenu().mouseReleased(e);
            }
            default -> {
                // Handle default case
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
        // System.out.println("prima del mouse Move il controller è: " + deathController.isPresent());
        switch (GameState.state) {
            case MENU -> {    
            /*scenePanel.getController()*/menuController.get().getMenu().mouseMoved(e);        
            }
            case SHOP -> {   
            // Handle shop event        
            }
            case DEAD -> {
                // if(deathController.isPresent()) {
                //     System.out.println("tutto okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                // } else {
                //     System.out.println("ERRORE");
                // }
                deathController.get().getMenu().mouseMoved(e);
            }            

            default -> {
                // Handle default case
            }
        }
    }
}