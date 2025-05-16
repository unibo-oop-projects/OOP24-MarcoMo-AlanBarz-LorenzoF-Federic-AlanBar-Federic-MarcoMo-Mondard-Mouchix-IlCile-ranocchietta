package frogger.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;

import frogger.common.GameState;
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

    public void updateButtons() {
        this.removeAll(); // Remove all existing buttons
        AtomicInteger counterX = new AtomicInteger(-5);
        AtomicInteger counterY = new AtomicInteger(5);
        this.getController().getPurchasableObject().forEach(purchasableObject -> {
            addButtonForObject(purchasableObject, counterX, counterY);
        });

        JButton backButton = new JButton("Menu");
        backButton.addActionListener((e) -> {
            GameState.state = GameState.MENU;
        });
        backButton.setBounds((int)this.getController().getXinPixel(-7), (int)this.getController().getYinPixel(6), 100, 30); // Set position and size of the button
        this.add(backButton);
        this.revalidate();
        this.repaint();
    }

    private void addButtonForObject(PurchasableObject purchasableObject, AtomicInteger x, AtomicInteger y) {
        // Crea e posiziona il bottone come già fai
        String img = purchasableObject.getImage();
        BufferedImage bufferedImage = LoadSave.GetSprite(img);
        int imgX = (int) this.getController().getXinPixel(x.get());
        int imgY = (int) this.getController().getYinPixel(y.get());

        // Calcola la posizione del bottone sotto l'immagine
        int buttonX = imgX;
        int buttonY = imgY + bufferedImage.getHeight() + 10; // 10 pixel di margine sotto l'immagine

        // Crea il bottone
        JButton jb;
        if (purchasableObject.isAvailable()) {
            if(this.getController().getGameController().getSkin().equals(img)){
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
                if(this.getController().getGameController().getCoins() >= purchasableObject.getPrize()) {
                    this.getController().getGameController().setCoins(
                        this.getController().getGameController().getCoins() - purchasableObject.getPrize());
                    this.getController().getGameController().setSkin(img);
                    System.out.println(this.getController().getGameController().getSkin());
                    purchasableObject.setAvailable(true);
                    this.getController().updateShop();
                    this.updateButtons();
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

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        AtomicInteger counterX = new AtomicInteger(-5);
        AtomicInteger counterY = new AtomicInteger(5);
        for (PurchasableObject purchasableObject : this.getController().getPurchasableObject()) {
            drawObjectImage(purchasableObject, counterX, counterY, g);
        }
    }

    @Override
    protected void importImg() {
        this.setBackgroundImage(LoadSave.GetSprite(LoadSave.GAME_BACKGROUND));
    }

    private void drawObjectImage(PurchasableObject purchasableObject, AtomicInteger x, AtomicInteger y, Graphics g) {
        String img = purchasableObject.getImage();
        BufferedImage bufferedImage = LoadSave.GetSprite(img);
        int imgX = (int) this.getController().getXinPixel(x.get());
        int imgY = (int) this.getController().getYinPixel(y.get());
        g.drawImage(bufferedImage, imgX, imgY, bufferedImage.getWidth(), bufferedImage.getHeight(), null);

        // Aggiorna le coordinate come fai per i bottoni
        if(x.get() < 3){
            x.set(x.get() + 2);
        } else {
            x.set(0);
            y.incrementAndGet();
        }
    }
}