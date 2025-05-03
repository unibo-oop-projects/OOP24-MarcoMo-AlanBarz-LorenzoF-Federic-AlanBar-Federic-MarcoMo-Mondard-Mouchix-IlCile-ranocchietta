package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.controller.ShopController;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.implementations.Skin;
import frogger.model.interfaces.PurchasableObjectFactory;

public class ShopInitTest {

    private ShopController shopController;
    private static final String FILE_NAME = "/shop.txt";
    private PurchasableObjectFactory factory;

    @BeforeEach
    void setUp() {
        this.shopController = new ShopController();
        this.shopController.shopInit();
        this.factory = new PurchasableObjectFactoryImpl();
    }

    @Test
    void testShopInit() {
        try(final BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME )))) {
            String line = r.readLine();
            assertEquals("Skin 10 /ranocchietta.png true", line);
            String[] values = line.split(" ");
            assertEquals("Skin", values[0]);
            assertEquals(10, Integer.parseInt(values[1])); 
            assertEquals("/ranocchietta.png", values[2]); 
            assertTrue(Boolean.parseBoolean(values[3]));

            Skin skin = factory.createSkin(
                Integer.parseInt(values[1]), 
                ImageIO.read(getClass().getResourceAsStream(values[2])),
                Boolean.parseBoolean(values[3])
            );

            assertEquals(10, skin.getPrize());
            assertTrue(skin.isAvailable());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
