package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.controller.GameControllerImpl;

public class GamePanel extends JPanel{
    GameControllerImpl controller;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT/2);
    private BufferedImage img;
    private BufferedImage background;
    private BufferedImage heart;
    private BufferedImage[] idleAni;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 15;  
    private BufferedImage deathImage;
    private long deathTime;
    private final int respawnDelay = 20000; // 2 secondi

    public GamePanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    private void importImg() {
        InputStream isImg = getClass().getResourceAsStream("/sprites.png");
        InputStream backgroundStream = getClass().getResourceAsStream("/background.png");
        InputStream heartStream = getClass().getResourceAsStream("/heart.png");
        InputStream deathStream = getClass().getResourceAsStream("/death.png");
        
        try {
            if (isImg == null || backgroundStream == null || heartStream == null || deathStream == null) {
                throw new IOException("One or more resource files not found in the classpath.");
            }

            img = ImageIO.read(isImg);
            background = ImageIO.read(backgroundStream);
            heart = ImageIO.read(heartStream); 
            deathImage = ImageIO.read(deathStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    public void setController(GameControllerImpl controller) {
        this.controller = controller;
        importImg();
        loadAnimations();
        setInputListener();
    }

    private void setInputListener(){
        this.addKeyListener(this.getController().getKeyListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        this.paintLevel(g);
    } 

    private void paintLevel(Graphics g){
        super.paintComponent(g);
        this.updateAnimationTick();

        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);

        for(int i = 0; i < this.controller.getGame().getPlayer().getLives(); i++){
            g.drawImage(heart, (int)this.controller.getXinPixel(i + Constants.MIN_X) , 0, null);
        }

        //drowing the obstacles
        for(var obstacle : controller.getGame().getObstacles()) {
            g.drawImage(obstacle.getImage(), (int)this.controller.getXinPixel(obstacle.getPos().x()), 
                (int)this.controller.getYinPixel(obstacle.getPos().y()), 
                obstacle.getDimension().width() * Constants.BLOCK_WIDTH, 
                obstacle.getDimension().height() * Constants.BLOCK_HEIGHT, null);
        }
        
        // Drawing the Player with rotation
        Graphics2D g2d = (Graphics2D) g;
        var player = controller.getGame().getPlayer();
        BufferedImage playerImage = player.getImage(); 
        int playerX = (int) controller.getXinPixel(player.getPos().x());
        int playerY = (int) controller.getYinPixel(player.getPos().y());
        int playerWidth = player.getDimension().width() * Constants.BLOCK_WIDTH;
        int playerHeight = player.getDimension().height() * Constants.BLOCK_HEIGHT;

        if(player.isRespawning()){
            if (deathTime == 0) { // Imposta deathTime solo la prima volta
                deathTime = System.currentTimeMillis();
            }
            g.drawImage(deathImage, playerX, playerY, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT, null);

            // Controlla se è ora di far ricomparire il giocatore
            if (System.currentTimeMillis() - deathTime >= respawnDelay) {
                player.resetPosition(); // Metodo per ripristinare la posizione del giocatore
                deathTime = 0; // Resetta deathTime per la prossima collisione
            } 
        } else {
            // Calculate the rotation angle based on the direction
            double angle = switch (player.getDirection()) {
                case Direction.UP -> Math.PI;
                case Direction.RIGHT -> -Math.PI / 2;
                case Direction.DOWN -> 0;
                case Direction.LEFT -> Math.PI / 2;
                default -> 0; // No rotation by default
            };

            // Apply the rotation
            g2d.rotate(angle, playerX + playerWidth / 2.0, playerY + playerHeight / 2.0);
            g2d.drawImage(playerImage, playerX, playerY, playerWidth, playerHeight, null);
            g2d.rotate(-angle, playerX + playerWidth / 2.0, playerY + playerHeight / 2.0); // Restore the rotation
        }

        g.setColor(Color.WHITE);
        g.setFont(myFont);
        g.drawString("Score: " + this.controller.getGame().getPlayer().getScore(), (int)this.controller.getXinPixel(Constants.MAX_X - 3), (int)this.controller.getYinPixel(Constants.MAX_Y - 0.5));
    }

    private void loadAnimations() {
        idleAni = new BufferedImage[5];

        for(int i = 0; i < idleAni.length; i++) {
            idleAni[i] = img.getSubimage(i*57,5,57,70);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        //TODO: add the check if the frog is jumping
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= idleAni.length) {
                aniIndex = 0;
            }
        }
    }

    public GameControllerImpl getController() {
        return controller;
    }
}
