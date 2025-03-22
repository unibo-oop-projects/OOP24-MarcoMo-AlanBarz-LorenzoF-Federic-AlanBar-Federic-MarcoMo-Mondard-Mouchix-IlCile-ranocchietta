package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.input.MoveDown;
import frogger.common.input.MoveLeft;
import frogger.common.input.MoveRight;
import frogger.common.input.MoveUp;
import frogger.controller.ControllerImpl;
import frogger.model.implementations.CarImpl;

public class ScenePanel extends JPanel implements KeyListener{
    ControllerImpl controller;
    private BufferedImage frog;
    private BufferedImage carLeft;
    private BufferedImage carRight;
    private BufferedImage trunk;

    public ScenePanel() {
        this.addKeyListener(this);
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
        importImg();
    }

    private void importImg() {
        InputStream isFrog = getClass().getResourceAsStream("/ranocchietta.png");
        InputStream isCarLeft = getClass().getResourceAsStream("/carLeft.png");
        InputStream isCarRight = getClass().getResourceAsStream("/carRight.png");
        InputStream isTrunk = getClass().getResourceAsStream("/trunk.png");
        
        try {
            frog = ImageIO.read(isFrog);
            carLeft = ImageIO.read(isCarLeft);
            carRight = ImageIO.read(isCarRight);
            trunk = ImageIO.read(isTrunk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    public void setController(ControllerImpl controller) {
        this.controller = controller;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        //drowing the frog
        g.drawImage(frog, (int)controller.getXinPixel(controller.getGame().getPlayer().getPos().x()), 
        (int)controller.getYinPixel(controller.getGame().getPlayer().getPos().y()),
        controller.getGame().getPlayer().getDimension().width() * Constants.BLOCK_WIDTH, controller.getGame().getPlayer().getDimension().height() * Constants.BLOCK_WIDTH, 
        null);


        this.controller.getGame().getPlayer().drawHitBox(g, (int)this.controller.getXinPixel(this.controller.getGame().getPlayer().getPos().x()),
        (int)this.controller.getYinPixel(this.controller.getGame().getPlayer().getPos().y()));
        
        //drowing the obstacles
        for(var obstacle : controller.getGame().getObstacles()) {
            //TODO: put the sprite for all the type of obstacles
            g.drawImage((obstacle instanceof CarImpl? (obstacle.getDirection().equals(Direction.LEFT)? carLeft : carRight) : trunk),
                (int)controller.getXinPixel(obstacle.getPos().x()), (int)controller.getYinPixel(obstacle.getPos().y()),
                obstacle.getDimension().width() * Constants.BLOCK_WIDTH, obstacle.getDimension().height() * Constants.BLOCK_HEIGHT,
                null);

            obstacle.drawHitBox(g, (int)controller.getXinPixel(obstacle.getPos().x()), (int)this.controller.getYinPixel(obstacle.getPos().y()));
        }
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
