package frogger.model.implementations;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.model.interfaces.Button;

@Deprecated
public class PauseMenu {
private final Button[] buttons = new MenuButtons[3];

    public PauseMenu() {
        loadButtons();
    }
    
    private void loadButtons() {
        buttons[0] = new MenuButtons((int)(Constants.FRAME_WIDTH/2), (int)(Constants.FRAME_HEIGHT/2 - 70), 0, GameState.PLAYING);
        buttons[1] = new MenuButtons((int)(Constants.FRAME_WIDTH/2), (int)(Constants.FRAME_HEIGHT/2),      1, GameState.MENU);  
        buttons[2] = new MenuButtons((int)(Constants.FRAME_WIDTH/2), (int)(Constants.FRAME_HEIGHT/2 + 70), 2, GameState.QUIT);
    }

    public Button[] getButtonList(){        
        return buttons;
    }

    public void update(){
        for(Button button : buttons){
            button.update();
        }
    }

    public void draw(Graphics g){
        for(Button button : buttons){
            button.draw(g);
        }
    }

    public void mousePressed(MouseEvent e){
       for(Button button : buttons){
            if(isIn(e, button)){
                button.setMousePressed(true);
                break;
            }
        } 
    }

    public void mouseReleased(MouseEvent e){
       for(Button button : buttons){
            if(isIn(e, button)){
                if(button.isMousePressed()){
                    button.applyGameState();
                    break;
                }
            }
        } 
        resetButtons();
    }

    public void mouseMoved(MouseEvent e){
        for(Button button : buttons){
            button.setMouseOver(false);
        }
        for(Button button : buttons){
            if(isIn(e, button)){
                button.setMouseOver(true);
                break;
            }
        }
    }

    private void resetButtons() {
       for(Button button : buttons){
            button.resetBools();      
        } 
    }

    public boolean isIn(MouseEvent e, Button button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }
}
