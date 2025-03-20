package frogger.view;

import javax.swing.JFrame;

import frogger.common.Constants;

public class GameScene {
    private JFrame frame;
    
    public GameScene(ScenePanel panel) {
        frame = new JFrame("Ranocchietta");
        //frame.setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        
        this.frame.getContentPane().add(panel);
        panel.requestFocus();

        frame.pack();
        frame.setVisible(true);
        System.out.println(frame.getWidth() + ", " + frame.getHeight());
    }

}
