package frogger.model.implementations;

import java.awt.event.MouseEvent;
import static java.util.Arrays.asList;
import java.util.LinkedList;
import java.util.List;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.model.interfaces.Button;
import frogger.model.interfaces.Menu;

public class MenuImpl implements Menu{
    private final List<Button> buttons = new LinkedList<>();

    public MenuImpl(GameState ... states) {
        loadButtons(states);
    }

    private void loadButtons(GameState ... states) {
        int xPos = (int)(Constants.FRAME_WIDTH/2);
        int yPos = (int)(Constants.FRAME_HEIGHT/2);
        int i = 0;
        for (GameState state : states) {    
            int offset = (i - (int)(states.length / 2)) * (Constants.BUTTON_HEIGHT + Constants.BUTTONS_DISTANCE);
            int imgIndex = asList(GameState.values()).indexOf(state);
            buttons.add(new MenuButtons(xPos, yPos + offset, imgIndex, states[i]));
            i++;
        }
    }

    @Override
    public List<Button> getButtonList() {        
        return buttons;
    }

    @Override
    public void update() {
        for(Button button : buttons){
            button.update();
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
       for(Button button : buttons){
            if(isIn(e, button)){
                button.setMousePressed(true);
                break;
            }
        } 
    }

    @Override
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

    @Override
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
    
    @Override
    public boolean isIn(MouseEvent e, Button button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    private void resetButtons() {
       for(Button button : buttons){
            button.resetBools();      
        } 
    }
}