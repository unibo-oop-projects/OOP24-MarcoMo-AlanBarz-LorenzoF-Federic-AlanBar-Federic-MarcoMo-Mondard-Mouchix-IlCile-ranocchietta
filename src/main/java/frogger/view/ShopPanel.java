package frogger.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JButton;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.LoadSave;
import frogger.common.Position;
import frogger.controller.ShopController;
import frogger.model.interfaces.PurchasableObject;

/**
 * Panel representing the shop interface where the player can buy and equip skins.
 * Handles the display of purchasable objects, their images, and related buttons.
 */
public class ShopPanel extends AbstractPanel<ShopController> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ShopPanel and sets its properties.
     */
    public ShopPanel() {
        super.setFocusable(true);
        super.setPanelSize();
    }

    /**
     * {@inheritDoc}
     * No input listeners are set for the shop panel.
     */
    @Override
    protected void setInputListener() {
    }

    /**
     * Updates the buttons for all purchasable objects and adds the menu button.
     * Removes all existing buttons and recreates them based on the current state.
     */
    public void updateButtons() {
        this.removeAll(); // Remove all existing buttons
        final List<PurchasableObject> objects = this.getController().getPurchasableObject();
        for (int i = 0; i < objects.size(); i++) {
            final Position p = getGridPosition(i);
            addButtonForObject(objects.get(i), (int) p.x(), (int) p.y());
        }

        final JButton backButton = new JButton("Menu");
        backButton.addActionListener((e) -> {
            GameState.state = GameState.MENU;
        });

        backButton.setBounds((int) this.getController().getXinPixel(Constants.MIN_X), 
            (int) this.getController().getYinPixel(Constants.MAX_Y), 
            Constants.BUTTON_WIDTH_IN_PIXEL, 
            Constants.BUTTON_HEIGHT_IN_PIXEL); // Set position and size of the button
        this.add(backButton);
        this.revalidate();
        this.repaint();
    }

    /**
     * Adds a button for the given purchasable object at the specified grid position.
     *
     * @param purchasableObject the object for which to create the button
     * @param x the x grid coordinate
     * @param y the y grid coordinate
     */
    private void addButtonForObject(final PurchasableObject purchasableObject, final int x, final int y) {
        // Crea e posiziona il bottone come già fai
        final String img = purchasableObject.getImage();
        final BufferedImage bufferedImage = LoadSave.GetSprite(img);
        final int imgX = (int) this.getController().getXinPixel(x);
        final int imgY = (int) this.getController().getYinPixel(y);

        // Calcola la posizione del bottone sotto l'immagine
        final int buttonX = imgX;
        final int buttonY = imgY + bufferedImage.getHeight() + 10; // 10 pixel di margine sotto l'immagine

        // Create the button
        final JButton jb;
        if (purchasableObject.isAvailable()) {
            if (this.getController().getGameController().getSkin().equals(img)) {
                jb = new JButton("Equipped");
            } else {
                jb = new JButton("Equip");
                jb.addActionListener((e) -> {
                    this.getController().getGameController().setSkin(img);
                    this.updateButtons();
                });
            }
        } else {
            jb = new JButton("Buy " + purchasableObject.getPrize());
            jb.addActionListener((e) -> {
                if (this.getController().getGameController().getCoins() >= purchasableObject.getPrize()) {
                    this.getController().getGameController().setCoins(
                        this.getController().getGameController().getCoins() - purchasableObject.getPrize());

                    this.getController().getGameController().setSkin(img);
                    purchasableObject.setAvailable(true);
                    this.getController().updateShop();
                    this.updateButtons();
                }
            });
        }

        this.setLayout(null);
        jb.setBounds(buttonX, buttonY, 
            Constants.BUTTON_WIDTH_IN_PIXEL, 
            Constants.BUTTON_HEIGHT_IN_PIXEL);
        this.add(jb);
    }

    /**
     * {@inheritDoc}
     * Paints the background and all purchasable object images.
     *
     * @param g the Graphics context to use for painting
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        final List<PurchasableObject> objects = this.getController().getPurchasableObject();
        for (int i = 0; i < objects.size(); i++) {
            final Position p = getGridPosition(i);
            drawObjectImage(objects.get(i), (int) p.x(), (int) p.y(), g);
        }
    }

    /**
     * {@inheritDoc}
     * Loads and sets the background image for the shop panel.
     */
    @Override
    protected void importImg() {
        this.setBackgroundImage(LoadSave.GetSprite(LoadSave.GAME_BACKGROUND));
    }

    /**
     * Draws the image of a purchasable object at the specified grid position.
     *
     * @param purchasableObject the object whose image to draw
     * @param x the x grid coordinate
     * @param y the y grid coordinate
     * @param g the Graphics context to use for painting
     */
    private void drawObjectImage(final PurchasableObject purchasableObject, final int x, final int y, final Graphics g) {
        final String img = purchasableObject.getImage();
        final BufferedImage bufferedImage = LoadSave.GetSprite(img);
        final int imgX = (int) this.getController().getXinPixel(x);
        final int imgY = (int) this.getController().getYinPixel(y);
        g.drawImage(bufferedImage, imgX, imgY, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }

    /**
     * Calculates the grid position for a given index.
     *
     * @param index the index of the object in the list
     * @return the Position representing the grid coordinates
     */
    private Position getGridPosition(final int index) {
        final int columns = 5; // 0,2,4,6 (x aumenta di 2)
        final int x = index % columns * 2 - 5; // -5, -3, -1, 1
        final int y = 5 - index / columns * 2;
        return new Position(x, y);
    }
}
