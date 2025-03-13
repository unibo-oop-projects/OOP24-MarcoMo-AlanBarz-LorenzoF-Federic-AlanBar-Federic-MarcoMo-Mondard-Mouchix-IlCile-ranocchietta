package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import frogger.common.Costants;
import frogger.common.input.InputControllerImpl;
import frogger.common.input.MoveDown;
import frogger.common.input.MoveLeft;
import frogger.common.input.MoveRight;
import frogger.common.input.MoveUp;
import frogger.controller.ControllerImpl;

public class ScenePanel extends JPanel implements KeyListener{
    ControllerImpl controller;
    InputControllerImpl inputController = new InputControllerImpl();
    private BufferedImage frog;
    private BufferedImage car;

    public ScenePanel() {
        setPanelSize();
        setBackground(Color.BLACK);
        importImg();
    }

    private void importImg() {
        InputStream isFrog = getClass().getResourceAsStream("/ranocchietta.png");
        InputStream isCar = getClass().getResourceAsStream("/car.png");

        try {
            frog = ImageIO.read(isFrog);
            car = ImageIO.read(isCar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Costants.FRAME_WIDTH, Costants.FRAME_HEIGHT));
    }

    public void setController(ControllerImpl controller) {
        this.controller = controller;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
    
        g.drawImage(frog, (int)controller.getGame().getPlayer().getPos().x(), (int)controller.getGame().getPlayer().getPos().y(), null);
        g.drawImage(car, 100, 300, null);
        for(var obstacle : controller.getGame().getObstacles()) {
            // g.setColor(Color.RED);
            // g.fillRect((int)obstacle.getPos().x(), (int)obstacle.getPos().y(), obstacle.getDimension().width(), obstacle.getDimension().height());
            g.drawImage(car, (int)obstacle.getPos().x(), (int)obstacle.getPos().y(), null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38){
            inputController.notifyCommand(new MoveUp());;
        } else if (e.getKeyCode() == 40){
            inputController.notifyCommand(new MoveDown());;
        } else if (e.getKeyCode() == 39){
            inputController.notifyCommand(new MoveRight());;
        } else if (e.getKeyCode() == 37){
            inputController.notifyCommand(new MoveLeft());;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
