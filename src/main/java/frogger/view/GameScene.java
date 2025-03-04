package frogger.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameScene {
    private JFrame frame;
    
    public GameScene(ScenePanel panel) {
        frame = new JFrame("Ranocchietta");
        frame.setPreferredSize(new Dimension(600, 800));    //TODO: take the dimension from controller
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        this.frame.add(panel);
        panel.requestFocus();

        frame.pack();
        frame.setVisible(true);
    }

}
