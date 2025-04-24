package frogger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frogger.common.Constants;

public class GameScene{
    private JFrame frame;
    
    public GameScene(/*JPanel panel*/) {
        frame = new JFrame("Ranocchietta");

        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        
        // this.frame.getContentPane().add(panel);
        // panel.requestFocus();

        frame.setLocationRelativeTo(null);
        
    }

    public void setPanel(JPanel panel) {
        this.frame.getContentPane().add(panel);
        panel.requestFocus();
        frame.pack();
        frame.setVisible(true);
    }
}
