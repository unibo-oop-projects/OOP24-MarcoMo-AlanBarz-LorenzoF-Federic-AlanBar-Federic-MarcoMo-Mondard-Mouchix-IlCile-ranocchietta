package frogger.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.common.Position;
import frogger.controller.DeathController;

public class DeathPanel extends AbstractPanel<DeathController>{
    private final static Position FONT_POS = new Position(-4, 2);

    private final int score;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT);

    public DeathPanel(int score) {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
        this.setOpaque(false);
        this.score = score;
    }

    @Override
    protected void setInputListener() {
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        paintBackground(g);
        paintScore(g);
        // g.drawImage(buttonBack, (Constants.FRAME_WIDTH/2 -  (buttonBack.getWidth()+30)/2), (Constants.FRAME_HEIGHT/2 -  (buttonBack.getHeight()+30)/2), 
                        // buttonBack.getWidth()+30, buttonBack.getHeight()+30, null);
        this.getController().getMenu().draw(g);
    }

    public void paintScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(myFont);
        g.drawString("YOUR SCORE: " + this.score, 
        (int)this.getController().getXinPixel(FONT_POS.x()), (int)this.getController().getYinPixel(FONT_POS.y()));
    }

    @Override
    protected void importImg() {
        // buttonBack = LoadSave.GetSprite("death_background.png");
        this.setBackgroundImage(LoadSave.GetSprite("death_background.png"));
    };

}
