package frogger.view;

import java.awt.Color;
import java.awt.Graphics;

import frogger.controller.GameControllerImpl;

/**
 * Represents the main game panel where the Frogger game is rendered.
 * This panel is responsible for delegating the drawing of game elements
 * to the {@link LevelPainter} and for setting input listeners.
 */
public class GamePanel extends AbstractPanel<GameControllerImpl> {
    private LevelPainter painter;

    /**
     * Constructs the game panel, sets its focusable state, size,
     * and background color.
     */
    public GamePanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    
    /**
     * Paints the components of the panel by delegating to the {@link LevelPainter}.
     *
     * @param g the {@link Graphics} object used for drawing
     */
    public void paintComponent(final Graphics g) {
        painter.paintLevel(g);
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    protected void importImg() {
        painter = new LevelPainter(getController());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setInputListener() {
        this.addKeyListener(this.getController().getKeyListener());
    }
}
