package frogger.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import frogger.common.GameState;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.interfaces.PurchasableObject;
import frogger.view.GameScene;
import frogger.view.ShopPanel;

public class ShopController extends AbstractController {

    private static final String FILE_NAME = "/shop.txt";
    private Set<PurchasableObject> purchasableObjects;
    private PurchasableObjectFactoryImpl factory;
    private ShopPanel shopPanel;
    private final MainControllerImpl mainController;

    public ShopController(MainControllerImpl mainController) {
        this.mainController = mainController;
    }
    
    public MainControllerImpl getMainController() {
        return mainController;
    }

    @Override
    public void init(GameScene gameScene) {
        this.purchasableObjects = new HashSet<>();
        this.factory = new PurchasableObjectFactoryImpl();
        this.shopInit();
        this.shopPanel = new ShopPanel();
        this.shopPanel.setController(this);
        gameScene.setPanel(shopPanel);
    }

    public Set<PurchasableObject> getPurchasableObject() {
        return this.purchasableObjects;
    }

    public void shopInit() {
        try(final BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME )))) {
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
}
