package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.controller.GameController;
import frogger.controller.GameControllerImpl;
import frogger.controller.ShopController;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.implementations.Skin;
import frogger.model.interfaces.PurchasableObjectFactory;
import frogger.view.GameScene;

public class ShopInitTest {

    private ShopController shopController;
    private static final GameController gameController = new GameControllerImpl();
    private static final String FILE_NAME = "/shop.txt";
    private PurchasableObjectFactory factoryPurchasable;

    @BeforeEach
    void setUp() {
        this.shopController = new ShopController(ShopInitTest.gameController);
        this.shopController.init(new GameScene());
        this.factoryPurchasable = new PurchasableObjectFactoryImpl();
        this.shopController.shopInit();
    }

    @Test
    void testShopInit() {
        try(final BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME )))) {
            String line = r.readLine();
            assertEquals("Skin 1 ranocchietta.png true", line);
            String[] values = line.split(" ");
            assertEquals("Skin", values[0]);
            assertEquals(1, Integer.parseInt(values[1])); 
            assertEquals("ranocchietta.png", values[2]); 
            assertTrue(Boolean.parseBoolean(values[3]));

            Skin skin = this.factoryPurchasable.createSkin(
                Integer.parseInt(values[1]), 
                values[2],
                Boolean.parseBoolean(values[3])
            );

            assertEquals(1, skin.getPrize());
            assertTrue(skin.isAvailable());

            ShopInitTest.gameController.getGame().setCoins(10);
            assertEquals(10, ShopInitTest.gameController.getGame().getCoins());

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}