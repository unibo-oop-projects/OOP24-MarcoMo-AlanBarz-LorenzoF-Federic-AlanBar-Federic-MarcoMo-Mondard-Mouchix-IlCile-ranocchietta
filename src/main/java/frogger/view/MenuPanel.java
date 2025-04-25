package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.controller.MenuControllerImpl;

public class MenuPanel extends AbstractPanel<MenuControllerImpl>{
    // MenuControllerImpl controller;

    public MenuPanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    // public void setController(MenuControllerImpl controller) {
    //     this.controller = controller;
    //     this.setInputListener();
    // }

    @Override
    protected void setInputListener(){
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        BufferedImage background = LoadSave.GetSprite(LoadSave.MENU_BACKGROUND);
        g.drawImage(background, (Constants.FRAME_WIDTH/2 -  (background.getWidth()+30)/2), (Constants.FRAME_HEIGHT/2 -  (background.getHeight()+30)/2), 
                        background.getWidth()+30, background.getHeight()+30, null);
        this.getController().getMenu().draw(g);
    }
    
    @Override
    protected void importImg() {};
}
