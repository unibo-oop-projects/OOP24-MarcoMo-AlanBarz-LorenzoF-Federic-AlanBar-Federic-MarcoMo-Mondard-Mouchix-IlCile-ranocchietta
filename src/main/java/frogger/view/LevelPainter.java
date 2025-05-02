package frogger.view;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.controller.Controller;
import frogger.model.interfaces.PlayerObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LevelPainter {
    private final Controller controller;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT/2);
    private BufferedImage background;
    private BufferedImage heart;
    private BufferedImage death;
    
    public LevelPainter(Controller controller) {
        this.controller = controller;
        // this.g = g;
        importImg();
    }

    public void paintLevel( Graphics g) {
        paintBackground(g);
        paintLives(g);
        
        paintObstacles(g);
        paintPlayer(g);
        paintScore(g);
    }

    public void paintObstacles(Graphics g) {
        for(var obstacle : getController().getGame().getObstacles()) {
            g.drawImage(obstacle.getImage(), (int)this.getController().getXinPixel(obstacle.getPos().x()), 
                (int)this.getController().getYinPixel(obstacle.getPos().y()), 
                obstacle.getDimension().width() * Constants.BLOCK_WIDTH, 
                obstacle.getDimension().height() * Constants.BLOCK_HEIGHT, null);
        }
    }

    public void paintPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        var player = getController().getGame().getPlayer();
        BufferedImage playerImage = player.getImage(); 
        int playerX = (int) getController().getXinPixel(player.getPos().x());
        int playerY = (int) getController().getYinPixel(player.getPos().y());
        int playerWidth = player.getDimension().width() * Constants.BLOCK_WIDTH;
        int playerHeight = player.getDimension().height() * Constants.BLOCK_HEIGHT;

        if(player.isDead()){
            g.drawImage(death, playerX, playerY, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT, null);
        } else {
            // Calculate the rotation angle based on the direction
            double angle = calculateRotation(getController().getGame().getPlayer());

            // Apply the rotation
            g2d.rotate(angle, playerX + playerWidth / 2.0, playerY + playerHeight / 2.0);
            g2d.drawImage(playerImage, playerX, playerY, playerWidth, playerHeight, null);
            g2d.rotate(-angle, playerX + playerWidth / 2.0, playerY + playerHeight / 2.0); // Restore the rotation
        }
        
    }

    private double calculateRotation(PlayerObject player) {
        return switch (player.getDirection()) {
            case Direction.UP -> Math.PI;
            case Direction.RIGHT -> -Math.PI / 2;
            case Direction.DOWN -> 0;
            case Direction.LEFT -> Math.PI / 2;
            default -> 0; // No rotation by default
        };
    }

    public void paintBackground(Graphics g) {
        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);
    }

    public void paintLives(Graphics g) {
        for(int i = 0; i < this.getController().getGame().getPlayer().getLives(); i++){
            g.drawImage(heart, (int)this.getController().getXinPixel(i + Constants.MIN_X) , 0, null);
        }
    }

    public void paintScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(myFont);
        g.drawString("Punteggio: " + this.getController().getGame().getPlayer().getScore(), 
        (int)this.getController().getXinPixel(Constants.MAX_X - 3), (int)this.getController().getYinPixel(Constants.MAX_Y - 0.5));
    }

    public void importImg() {
        getController().getGame().getPlayer().setImage("ranocchietta.png");
        InputStream backgroundStream = getClass().getResourceAsStream("/background.png");
        InputStream heartStream = getClass().getResourceAsStream("/heart.png");
        InputStream deathStream = getClass().getResourceAsStream("/death.png");
        
        try {
            background = ImageIO.read(backgroundStream);
            heart = ImageIO.read(heartStream);
            death = ImageIO.read(deathStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Controller getController() {
        return controller;
    }
}

