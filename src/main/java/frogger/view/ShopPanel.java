package frogger.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;

import frogger.common.LoadSave;
import frogger.controller.ShopController;
import frogger.model.interfaces.PurchasableObject;

public class ShopPanel extends AbstractPanel<ShopController>{

    public ShopPanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.CYAN);
    }

    @Override
    protected void setInputListener() {    
    }

    @Override
    public void paintComponent(final Graphics g) {
        AtomicInteger counterX = new AtomicInteger(-5);
        AtomicInteger counterY = new AtomicInteger(5);
        this.getController().getPurchasableObject().stream().forEach(purchasableObject -> drawObject(purchasableObject, g, counterX, counterY));

    }

    @Override
    protected void importImg() {
        // No images to import for the shop panel
    }

    private void drawObject(PurchasableObject purchasableObject, Graphics g, AtomicInteger x, AtomicInteger y) {
        String img = purchasableObject.getImage();
        BufferedImage bufferedImage = LoadSave.GetSprite(img);
        int imgX = (int) this.getController().getXinPixel(x.get());
        int imgY = (int) this.getController().getYinPixel(y.get());

        // Disegna l'immagine
        g.drawImage(bufferedImage, imgX, imgY, bufferedImage.getWidth(), bufferedImage.getHeight(), null);

        // Calcola la posizione del bottone sotto l'immagine
        int buttonX = imgX;
        int buttonY = imgY + bufferedImage.getHeight() + 10; // 10 pixel di margine sotto l'immagine

        // Crea il bottone
        JButton jb;
        if (purchasableObject.isAvailable()) {
            jb = new JButton("Equip");
            jb.addActionListener((e) -> {
                this.getController().getGameController().getGame().getPlayer().setImage(img);
            });
        } else {
            jb = new JButton("Buy " + purchasableObject.getPrize());
            jb.addActionListener((e) -> {
                if(this.getController().getGameController().getGame().getCoins() >= purchasableObject.getPrize()) {
                    this.getController().getGameController().getGame().setCoins(
                        this.getController().getGameController().getGame().getCoins() - purchasableObject.getPrize());
                    this.getController().getGameController().getGame().getPlayer().setImage(img);
                }
            });
        }

        // Imposta il layout su null per posizionamento manuale
        this.setLayout(null);
        jb.setBounds(buttonX, buttonY, 100, 30); // Larghezza 100px, altezza 30px
        this.add(jb);

        if(x.get() < 3){
            //x.incrementAndGet();
            x.set(x.get() +2);
        } else {
            x.set(0);
            y.incrementAndGet();
        }
    }
}
