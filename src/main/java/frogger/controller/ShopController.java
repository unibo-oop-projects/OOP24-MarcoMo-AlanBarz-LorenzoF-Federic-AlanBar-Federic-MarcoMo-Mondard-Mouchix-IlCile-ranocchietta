package frogger.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import frogger.common.GameState;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.interfaces.PurchasableObject;
import frogger.view.GameScene;
import frogger.view.ShopPanel;

/**
 * Controller for the shop scene. Handles initialization, loading and saving of purchasable objects,
 * and manages the shop panel UI.
 */
public class ShopController extends AbstractController {

    /** Name of the default shop resource file (read-only). */
    private static final String FILE_NAME = "/shop.txt";
    /** Name of the save file for shop data (writable). */
    private static final String SAVE_FILE = "shop_save.txt";
    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(ShopController.class.getName());
    /** List of purchasable objects available in the shop. */
    private final List<PurchasableObject> purchasableObjects;
    /** Factory for creating purchasable objects. */
    private final PurchasableObjectFactoryImpl factory;
    /** Reference to the shop panel UI. */
    private ShopPanel shopPanel;
    /** Reference to the main game controller. */
    private final GameController gameController;

    /**
     * Constructs a new ShopController.
     * Deletes the existing shop save file if present.
     * The passed GameController reference is stored and may be modified externally.
     *
     * @param gc the main game controller
     */
    public ShopController(final GameController gc) {
        final File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            if (!saveFile.delete()) {
                LOGGER.log(Level.WARNING, "Failed to delete the existing save file.");
            } else {
                LOGGER.log(Level.INFO, "Existing save file deleted successfully.");
            }
        }

        this.gameController = gc;
        this.purchasableObjects = new LinkedList<>();
        this.factory = new PurchasableObjectFactoryImpl();
    }

    /**
     * Returns the internal GameController reference.
     * Modifying the returned object will affect this ShopController.
     *
     * @return the game controller associated with this shop
     */
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * {@inheritDoc}
     * Initializes the shop scene, loads purchasable objects, and sets up the shop panel.
     */
    @Override
    public void init(final GameScene gameScene) {
        this.shopInit();
        this.shopPanel = new ShopPanel();
        this.shopPanel.setController(this);
        this.shopPanel.updateButtons();
        gameScene.setPanel(shopPanel);
    }

    /**
     * Returns a copy of the list of purchasable objects available in the shop.
     *
     * @return a new list containing the purchasable objects
     */
    public List<PurchasableObject> getPurchasableObject() {
        return new LinkedList<>(this.purchasableObjects);
    }

    /**
     * Initializes the shop by loading purchasable objects from the save file if it exists,
     * or from the default resource file otherwise.
     * Populates the internal list of purchasable objects.
     */
    public void shopInit() {
        InputStream is = null;
        try {
            final File saveFile = new File(SAVE_FILE);
            if (saveFile.exists()) {
                is = new FileInputStream(saveFile);
            } else {
                is = ShopController.class.getResourceAsStream(FILE_NAME);
            }

            try (BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line = r.readLine();
                while (line != null) {
                    final String[] values = line.split(" ");
                    if ("Skin".equals(values[0])) {
                        this.purchasableObjects.add(factory.createSkin(
                            Integer.parseInt(values[1]), 
                            values[2],
                            Boolean.parseBoolean(values[3])
                        ));
                    }
                    line = r.readLine();
                }
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading the shop file", e);
        }
    }

    /**
     * {@inheritDoc}
     * Repaints the shop panel during the controller's core loop.
     */
    @Override
    protected void core() {
        this.shopPanel.repaint();
    }

    /**
     * {@inheritDoc}
     * The loop continues as long as the game state is SHOP.
     */
    @Override
    protected boolean loopCondition() {
        return GameState.state == GameState.SHOP;
    }

    /**
     * {@inheritDoc}
     * No additional changes are needed at the end of the loop.
     */
    @Override
    protected void changesLoopEnd() { }

    /**
     * Saves the current state of purchasable objects to the shop save file.
     * Uses UTF-8 encoding for writing.
     */
    public void updateShop() {
        try (BufferedWriter w = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(SAVE_FILE), StandardCharsets.UTF_8))) {
            for (final PurchasableObject purchasableObject : this.purchasableObjects) {
                w.write(purchasableObject.toString());
                w.newLine();
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to the shop save file", e);
        }
    }
}
