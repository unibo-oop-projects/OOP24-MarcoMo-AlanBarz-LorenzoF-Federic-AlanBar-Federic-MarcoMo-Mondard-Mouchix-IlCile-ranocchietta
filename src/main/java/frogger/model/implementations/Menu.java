package frogger.model.implementations;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.State;
import frogger.view.MenuButtons;

public class Menu extends State{

    private final MenuButtons[] buttons = new MenuButtons[3];

    public Menu(GameImpl game) {
        super(game); 
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButtons((int)(Constants.FRAME_WIDTH/2), (int)(Constants.FRAME_HEIGHT/2 - 70), 0, GameState.PLAYING);
        buttons[1] = new MenuButtons((int)(Constants.FRAME_WIDTH/2), (int)(Constants.FRAME_HEIGHT/2),      1, GameState.SHOP);  
        buttons[2] = new MenuButtons((int)(Constants.FRAME_WIDTH/2), (int)(Constants.FRAME_HEIGHT/2 + 70), 2, GameState.QUIT);
    }

    public void update(){
        for(MenuButtons button : buttons){
            button.update();
        }
    }

    public void draw(Graphics g){
        for(MenuButtons button : buttons){
            button.draw(g);
        }
    }

    public void mousePressed(MouseEvent e){
       for(MenuButtons button : buttons){
            if(isIn(e, button)){
                button.setMousePressed(true);
                break;
            }
        } 
    }
    public void mouseReleased(MouseEvent e){
       for(MenuButtons button : buttons){
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
        for(MenuButtons button : buttons){
            button.setMouseOver(false);
        }
        for(MenuButtons button : buttons){
            if(isIn(e, button)){
                button.setMouseOver(true);
                break;
            }
        }
    }

    private void resetButtons() {
       for(MenuButtons button : buttons){
            button.resetBools();      
        } 
    }

    

}