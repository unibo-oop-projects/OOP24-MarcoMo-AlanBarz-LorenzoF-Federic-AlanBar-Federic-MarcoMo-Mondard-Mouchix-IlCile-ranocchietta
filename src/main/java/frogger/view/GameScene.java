package frogger.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import frogger.common.Costants;

public class GameScene {
    private JFrame frame;
    
    public GameScene(ScenePanel panel) {
        frame = new JFrame("Ranocchietta");
        frame.setPreferredSize(new Dimension(Costants.FRAME_WIDTH, Costants.FRAME_HEIGHT));    //TODO: take the dimension from controller
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        this.frame.add(panel);
        panel.requestFocus();

        frame.pack();
        frame.setVisible(true);
    }

}
