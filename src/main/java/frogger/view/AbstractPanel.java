package frogger.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import frogger.common.Constants;

/**
 * An abstract base class for game panels.
 * Provides common functionality for managing the controller,
 * background image, and panel dimensions.
 *
 * @param <X> the type of controller associated with this panel
 */
public abstract class AbstractPanel<X> extends JPanel implements Panel<X> {
    private X controller;
    private BufferedImage background;
    
    /**
     * Sets up input listeners.
     * This method must be implemented by subclasses to handle user input.
     */
    protected abstract void setInputListener();

    /**
     * Imports all necessary images for this panel.
     * This method must be implemented by subclasses to load resources.
     */
    protected abstract void importImg();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final X controller) {
        this.controller = controller;
        this.importImg();
        this.setInputListener();
    }

    /**
     * Sets the preferred size of the panel based on predefined constants.
     */
    protected void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    /**
     * Returns the controller associated with this panel.
     *
     * @return the controller
     */
    protected X getController() {
        return controller;
    }

    /**
     * Paints the background image on the panel.
     *
     * @param g the Graphics context to draw on
     */
    protected void paintBackground(final Graphics g) {
        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);
    }

    /**
     * Returns the background image used by this panel.
     *
     * @return the background image
     */
    protected BufferedImage getBackgroundImage() {
        return background;
    }

    /**
     * Sets the background image for this panel.
     *
     * @param background the background image to set
     */
    protected void setBackgroundImage(final BufferedImage background) {
        this.background = background;
    }
}
