package frogger.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.LoadSave;
import frogger.controller.GameController;
import frogger.controller.PauseController;

public class PausePanel extends AbstractPanel<PauseController>{

    private BufferedImage background;

    public PausePanel() {
        setFocusable(true);
        setPanelSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setInputListener() {
        this.addMouseListener(this.getController().getMouseListener());
        this.addMouseMotionListener(this.getController().getMouseMotionListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void importImg() {
        background = LoadSave.GetSprite("pause_background.jpg");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        paintBackground(g);
        this.getController().getMenu().draw(g);
    }

    protected void paintBackground(Graphics g) {
        LevelPainter p = new LevelPainter((GameController)getController().getGameController());
        p.paintLevel(g);
        g.drawImage(background, (int)getController().getXinPixel(-3.5) , (int)getController().getYinPixel(3), 
        Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT / 2, null);
    }

}
