package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.input.KeyInput;
import frogger.common.input.MouseInput;
import frogger.controller.ControllerImpl;

public class ScenePanel extends JPanel{
    ControllerImpl controller;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT/2);
    private BufferedImage img;
    private BufferedImage background;
    private BufferedImage heart;
    private BufferedImage[] idleAni;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 15;   

    public ScenePanel() {
        KeyInput keyInput = new KeyInput(this);
        this.addKeyListener(keyInput);
        MouseInput mouseInput = new MouseInput(this);
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    private void importImg() {
        InputStream isFrog = getClass().getResourceAsStream("/ranocchietta.png");
        controller.getGame().getPlayer().setImage(isFrog);
        InputStream isImg = getClass().getResourceAsStream("/sprites.png");
        InputStream backgroundStream = getClass().getResourceAsStream("/background.png");
        InputStream heartStream = getClass().getResourceAsStream("/heart.png");
        
        
        try {
            img = ImageIO.read(isImg);
            background = ImageIO.read(backgroundStream);
            heart = ImageIO.read(heartStream); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    public void setController(ControllerImpl controller) {
        this.controller = controller;
        importImg();
        loadAnimations();
    }

    @Override
    public void paintComponent(final Graphics g) {
        switch (GameState.state) {
            case PLAYING -> {
                this.paintLevel(g);
            }
            case MENU -> {
                this.controller.getGame().getMenu().draw(g);
            }            
        }
    } 

    private void paintLevel(Graphics g){
        super.paintComponent(g);
        this.updateAnimationTick();

        g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);

        //RasterImage image = (RasterImage) Image.load("image.bmp");

        for(int i = 0; i < this.controller.getGame().getPlayer().getLives(); i++){
            g.drawImage(heart, (int)this.controller.getXinPixel(i + Constants.MIN_X) , 0, null);
        }

        //drowing the obstacles
        for(var obstacle : controller.getGame().getObstacles()) {
            g.drawImage(obstacle.getImage(), (int)this.controller.getXinPixel(obstacle.getPos().x()), 
            (int)this.controller.getYinPixel(obstacle.getPos().y()), 
            obstacle.getDimension().width() * Constants.BLOCK_WIDTH, 
            obstacle.getDimension().height() * Constants.BLOCK_HEIGHT, null);
            
            obstacle.drawHitBox(g, (int)this.controller.getXinPixel(obstacle.getPos().x()), (int)this.controller.getYinPixel(obstacle.getPos().y()));
        }
        
        g.drawImage( controller.getGame().getPlayer().getImage() /*idleAni[aniIndex]*/, (int)controller.getXinPixel(controller.getGame().getPlayer().getPos().x()), 
        (int)controller.getYinPixel(controller.getGame().getPlayer().getPos().y()), 
        controller.getGame().getPlayer().getDimension().width() * Constants.BLOCK_WIDTH, 
        controller.getGame().getPlayer().getDimension().height() * Constants.BLOCK_HEIGHT, null);

        this.controller.getGame().getPlayer().drawHitBox(g, (int)this.controller.getXinPixel(this.controller.getGame().getPlayer().getPos().x()),
        (int)this.controller.getYinPixel(this.controller.getGame().getPlayer().getPos().y()));

        g.setColor(Color.WHITE);
        g.setFont(myFont);
        g.drawString("Punteggio: " + this.controller.getGame().getPlayer().getScore(), (int)this.controller.getXinPixel(Constants.MAX_X - 3), (int)this.controller.getYinPixel(Constants.MAX_Y - 0.5));
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

    public ControllerImpl getController() {
        return controller;
    }
}
