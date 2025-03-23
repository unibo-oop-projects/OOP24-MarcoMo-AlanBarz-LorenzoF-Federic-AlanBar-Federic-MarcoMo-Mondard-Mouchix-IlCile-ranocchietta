package frogger.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;

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

    public ScenePanel() {
        this.addKeyListener(this);
        setFocusable(true);
        setPanelSize();
        setBackground(Color.BLACK);
    }

    private void importImg() {
        InputStream isFrog = getClass().getResourceAsStream("/ranocchietta.png");
        controller.getGame().getPlayer().setImage(isFrog);
        controller.getGame().getObstacles().forEach(obstacle -> {
            System.out.println(obstacle.getClass().getInterfaces()[0].getSimpleName());
            if (obstacle instanceof CarImpl) {
                obstacle.setImage((obstacle.getDirection().equals(Direction.LEFT)? getClass().getResourceAsStream("/carLeft.png") 
                : getClass().getResourceAsStream("/carRight.png")));
            } else {
                obstacle.setImage(getClass().getResourceAsStream("/" + obstacle.getClass().getInterfaces()[0].getSimpleName().toLowerCase() + ".png"));
            }
        });

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
            obstacle.render(g,(int)controller.getXinPixel(obstacle.getPos().x()), (int)controller.getYinPixel(obstacle.getPos().y()));

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
