package frogger.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import frogger.common.Constants;
import frogger.model.implementations.PurchasableObjectFactoryImpl;
import frogger.model.interfaces.PurchasableObject;

public class ShopController {

    private static final String FILE_NAME = "shop.txt";
    private final Set<PurchasableObject> purchasableObjects = new HashSet<>();
    private final PurchasableObjectFactoryImpl factory = new PurchasableObjectFactoryImpl();

    public ShopController() {
        shopInit();
    }

    public Set<PurchasableObject> getPurchasableObject() {
        return this.purchasableObjects;
    }

    public void shopInit() {
        try(final BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME ),"UTF -16 "))) {
            while(!r.readLine().isEmpty()){
                String line = r.readLine();
                String[] values = line.split(" ");
                if(values[0] == "Skin"){
                    this.purchasableObjects.add(factory.createSkin(Integer.parseInt(values[1]), ImageIO.read(getClass().getResourceAsStream(System.getProperty(Constants.PROP_FILE_SEPARATOR) + values[2]))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
