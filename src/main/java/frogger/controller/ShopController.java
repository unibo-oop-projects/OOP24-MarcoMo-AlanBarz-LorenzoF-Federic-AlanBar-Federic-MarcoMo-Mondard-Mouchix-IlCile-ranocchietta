package frogger.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import frogger.common.GameState;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.interfaces.PurchasableObject;
import frogger.view.GameScene;
import frogger.view.ShopPanel;

public class ShopController extends AbstractController {

    private static final String FILE_NAME = "/shop.txt"; // solo lettura
    private static final String SAVE_FILE = "shop_save.txt"; // scrivibile
    private List<PurchasableObject> purchasableObjects;
    private PurchasableObjectFactoryImpl factory;
    private ShopPanel shopPanel;
    private final GameController gameController;

    public ShopController(GameController gc) {
        File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            saveFile.delete();
        }

        this.gameController = gc;
        this.purchasableObjects = new LinkedList<>();
        this.factory = new PurchasableObjectFactoryImpl();
        this.shopInit();
    }
    
    public GameController getGameController() {
        return this.gameController;
    }

    @Override
    public void init(GameScene gameScene) {
        this.shopPanel = new ShopPanel();
        this.shopPanel.setController(this);
        this.shopPanel.updateButtons();
        gameScene.setPanel(shopPanel);
    }

    public List<PurchasableObject> getPurchasableObject() {
        return new LinkedList<>(this.purchasableObjects);
    }

    public void shopInit() {
        InputStream is = null;
        try {
            File saveFile = new File(SAVE_FILE);
            if (saveFile.exists()) {
                is = new FileInputStream(saveFile);
            } else {
                is = getClass().getResourceAsStream(FILE_NAME);
            }

            try (BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while((line = r.readLine()) != null){
                    String[] values = line.split(" ");
                    if("Skin".equals(values[0])){
                        this.purchasableObjects.add(factory.createSkin(
                            Integer.parseInt(values[1]), 
                            values[2],
                            Boolean.parseBoolean(values[3])
                        ));
                        System.out.println("Skin: " + values[1] + " " + values[2] + " " + values[3]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void core() {
        this.shopPanel.repaint();
    }

    @Override
    protected boolean loopCondition() {
        return GameState.state == GameState.SHOP;
    }

    @Override
    protected void changesLoopEnd() {}

    public void updateShop() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            for (PurchasableObject purchasableObject : this.purchasableObjects) {
                w.write(purchasableObject.toString());
                w.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
