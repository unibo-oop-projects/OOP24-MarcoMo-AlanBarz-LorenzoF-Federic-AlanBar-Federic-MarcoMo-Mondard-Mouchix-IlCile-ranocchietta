package frogger.view;

import java.awt.Color;
import java.awt.Graphics;

import frogger.controller.GameControllerImpl;

public class GamePanel extends AbstractPanel<GameControllerImpl>{
    private LevelPainter painter;

    public GamePanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    protected void importImg() {
        painter = new LevelPainter(getController());
    }

    protected void setInputListener(){
        this.addKeyListener(this.getController().getKeyListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        // this.paintLevel(g);
        painter.paintLevel(g);
    } 
}
