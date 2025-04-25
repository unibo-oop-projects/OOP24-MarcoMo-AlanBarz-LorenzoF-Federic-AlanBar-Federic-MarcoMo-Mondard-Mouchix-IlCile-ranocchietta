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

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.controller.GameControllerImpl;

public class GamePanel extends AbstractPanel<GameControllerImpl>{
    // GameControllerImpl controller;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT/2);
    // private BufferedImage img;
    private BufferedImage background;
    private BufferedImage heart;
    // private BufferedImage[] idleAni;
    // private int aniTick;
    // private int aniIndex;
    // private int aniSpeed = 15;   

    public GamePanel() {
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    protected void importImg() {
        InputStream isFrog = getClass().getResourceAsStream("/ranocchietta.png");
        getController().getGame().getPlayer().setImage(isFrog);
        // InputStream isImg = getClass().getResourceAsStream("/sprites.png");
        InputStream backgroundStream = getClass().getResourceAsStream("/background.png");
        InputStream heartStream = getClass().getResourceAsStream("/heart.png");
        
        
        try {
            // img = ImageIO.read(isImg);
            background = ImageIO.read(backgroundStream);
            heart = ImageIO.read(heartStream); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    protected void setInputListener(){
        this.addKeyListener(this.getController().getKeyListener());
    }

    @Override
    public void paintComponent(final Graphics g) {
        this.paintLevel(g);
    } 

    private void paintLevel(Graphics g){
        super.paintComponent(g);
        // this.updateAnimationTick();

        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);

        for(int i = 0; i < this.getController().getGame().getPlayer().getLives(); i++){
            g.drawImage(heart, (int)this.getController().getXinPixel(i + Constants.MIN_X) , 0, null);
        }

        //drowing the obstacles
        for(var obstacle : getController().getGame().getObstacles()) {
            g.drawImage(obstacle.getImage(), (int)this.getController().getXinPixel(obstacle.getPos().x()), 
                (int)this.getController().getYinPixel(obstacle.getPos().y()), 
                obstacle.getDimension().width() * Constants.BLOCK_WIDTH, 
                obstacle.getDimension().height() * Constants.BLOCK_HEIGHT, null);
        }
        
        // Drawing the Player with rotation
        Graphics2D g2d = (Graphics2D) g;
        var player = getController().getGame().getPlayer();
        BufferedImage playerImage = player.getImage(); 
        int playerX = (int) getController().getXinPixel(player.getPos().x());
        int playerY = (int) getController().getYinPixel(player.getPos().y());
        int playerWidth = player.getDimension().width() * Constants.BLOCK_WIDTH;
        int playerHeight = player.getDimension().height() * Constants.BLOCK_HEIGHT;

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

        g.setColor(Color.WHITE);
        g.setFont(myFont);
        g.drawString("Punteggio: " + this.getController().getGame().getPlayer().getScore(), (int)this.getController().getXinPixel(Constants.MAX_X - 3), (int)this.getController().getYinPixel(Constants.MAX_Y - 0.5));
    }

    // private void loadAnimations() {
    //     idleAni = new BufferedImage[5];

    //     for(int i = 0; i < idleAni.length; i++) {
    //         idleAni[i] = img.getSubimage(i*57,5,57,70);
    //     }
    // }

    // private void updateAnimationTick() {
    //     aniTick++;
    //     //TODO: add the check if the frog is jumping
    //     if(aniTick >= aniSpeed) {
    //         aniTick = 0;
    //         aniIndex++;
    //         if(aniIndex >= idleAni.length) {
    //             aniIndex = 0;
    //         }
    //     }
    // }
}
