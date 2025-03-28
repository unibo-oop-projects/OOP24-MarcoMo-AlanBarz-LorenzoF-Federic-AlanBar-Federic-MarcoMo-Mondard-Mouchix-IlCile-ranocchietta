package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import frogger.common.Constants;
import frogger.common.input.MoveDown;
import frogger.common.input.MoveLeft;
import frogger.common.input.MoveRight;
import frogger.common.input.MoveUp;
import frogger.controller.ControllerImpl;

public class ScenePanel extends JPanel implements KeyListener{
    ControllerImpl controller;
    private final Font myFont = new Font("MyFont", 1, Constants.BLOCK_HEIGHT/2);

    public ScenePanel() {
        this.addKeyListener(this);
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    private void importImg() {
        InputStream isFrog = getClass().getResourceAsStream("/ranocchietta.png");
        controller.getGame().getPlayer().setImage(isFrog);
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    public void setController(ControllerImpl controller) {
        this.controller = controller;
        importImg();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        InputStream backgroundStream = getClass().getResourceAsStream("/background.png");
        BufferedImage background;
        try {
            background = ImageIO.read(backgroundStream);
            g.drawImage(background, 0 , 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream heartStream = getClass().getResourceAsStream("/heart.png");
        try{
            BufferedImage heart = ImageIO.read(heartStream); 
            for(int i = 0; i < this.controller.getGame().getPlayer().getLives(); i++){
                g.drawImage(heart, (int)this.controller.getXinPixel(i + Constants.MIN_X) , 0, null);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        g.setColor(Color.WHITE);
        g.setFont(myFont);
        g.drawString("Punteggio: " + this.controller.getGame().getPlayer().getScore(), (int)this.controller.getXinPixel(Constants.MAX_X - 2), (int)this.controller.getYinPixel(Constants.MAX_Y - 0.5));
        
        //drowing the obstacles
        for(var obstacle : controller.getGame().getObstacles()) {
            obstacle.render(g, (int)this.controller.getXinPixel(obstacle.getPos().x()), (int)this.controller.getYinPixel(obstacle.getPos().y()));

            obstacle.drawHitBox(g, (int)this.controller.getXinPixel(obstacle.getPos().x()), (int)this.controller.getYinPixel(obstacle.getPos().y()));
        }

        //drowing the frog
        // g.drawImage(frog, (int)controller.getXinPixel(controller.getGame().getPlayer().getPos().x()), 
        // (int)controller.getYinPixel(controller.getGame().getPlayer().getPos().y()),
        // controller.getGame().getPlayer().getDimension().width() * Constants.BLOCK_WIDTH, controller.getGame().getPlayer().getDimension().height() * Constants.BLOCK_WIDTH, 
        // null);
        
        controller.getGame().getPlayer().render(g, (int)controller.getXinPixel(controller.getGame().getPlayer().getPos().x()), 
        (int)controller.getYinPixel(controller.getGame().getPlayer().getPos().y()));

        this.controller.getGame().getPlayer().drawHitBox(g, (int)this.controller.getXinPixel(this.controller.getGame().getPlayer().getPos().x()),
        (int)this.controller.getYinPixel(this.controller.getGame().getPlayer().getPos().y()));
        
        //drowing the obstacles
        for(var obstacle : controller.getGame().getObstacles()) {
            obstacle.render(g, (int)this.controller.getXinPixel(obstacle.getPos().x()), (int)this.controller.getYinPixel(obstacle.getPos().y()));

            obstacle.drawHitBox(g, (int)this.controller.getXinPixel(obstacle.getPos().x()), (int)this.controller.getYinPixel(obstacle.getPos().y()));
        }

        g.setColor(Color.WHITE);
        g.setFont(myFont);
        g.drawString("Punteggio: " + this.controller.getGame().getPlayer().getScore(), (int)this.controller.getXinPixel(Constants.MAX_X - 2), (int)this.controller.getYinPixel(Constants.MAX_Y - 0.5));
    } 

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38){
            this.controller.getInputController().notifyCommand(new MoveUp());;
        } else if (e.getKeyCode() == 40){
            this.controller.getInputController().notifyCommand(new MoveDown());
        } else if (e.getKeyCode() == 39){
            this.controller.getInputController().notifyCommand(new MoveRight());
        } else if (e.getKeyCode() == 37){
            this.controller.getInputController().notifyCommand(new MoveLeft());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
