package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    public ScenePanel() {
        setPanelSize();
        setBackground(Color.BLACK);
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
        
        for(var obstacle : controller.getGame().getObstacles()) {
            g.setColor(Color.RED);
            g.fillRect((int)obstacle.getPos().x(), (int)obstacle.getPos().y(), obstacle.getDimension().width(), obstacle.getDimension().height());
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
