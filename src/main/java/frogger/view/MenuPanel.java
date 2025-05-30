package frogger.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.controller.MenuControllerImpl;

public class MenuPanel extends AbstractPanel<MenuControllerImpl>{
    private BufferedImage buttonBack;

    public MenuPanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    @Override
    protected void setInputListener(){
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        paintBackground(g);
        paintButtonBack(g);
        paintMenu(g);
    }

    private void paintButtonBack(Graphics g) {
        g.drawImage(buttonBack, (Constants.FRAME_WIDTH / 2 - (buttonBack.getWidth() + 30) / 2), (Constants.FRAME_HEIGHT / 2 - (buttonBack.getHeight() + 30) / 2), 
                        buttonBack.getWidth() + 30, buttonBack.getHeight() + 30, null);
    }
    
    private void paintMenu(Graphics g) {
        this.getController().getMenu().getButtonList().forEach((button) -> {
            g.drawImage(button.getCurrentImg(), button.getXPos(), button.getYPos(), Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT, null);           
        });
    }

    @Override
    protected void importImg() {
        buttonBack = LoadSave.GetSprite(LoadSave.MENU_BUTTONBACK);
        this.setBackgroundImage(LoadSave.GetSprite(LoadSave.GAME_BACKGROUND));
    };
}
