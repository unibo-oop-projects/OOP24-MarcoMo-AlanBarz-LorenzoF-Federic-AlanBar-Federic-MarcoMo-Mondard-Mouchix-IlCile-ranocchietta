package frogger.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.controller.DeathController;

public class DeathPanel extends AbstractPanel<DeathController>{

    private BufferedImage buttonBack;
    private BufferedImage background;

    public DeathPanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }
    @Override
    protected void setInputListener() {
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        paintBackground(g);
        // g.drawImage(buttonBack, (Constants.FRAME_WIDTH/2 -  (buttonBack.getWidth()+30)/2), (Constants.FRAME_HEIGHT/2 -  (buttonBack.getHeight()+30)/2), 
                        // buttonBack.getWidth()+30, buttonBack.getHeight()+30, null);
        this.getController().getMenu().draw(g);
    }

    public void paintBackground(Graphics g) {
        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);
    }

    @Override
    protected void importImg() {
        // buttonBack = LoadSave.GetSprite(LoadSave.MENU_BUTTONBACK);
        background = LoadSave.GetSprite("death_background.png");
    };

}
