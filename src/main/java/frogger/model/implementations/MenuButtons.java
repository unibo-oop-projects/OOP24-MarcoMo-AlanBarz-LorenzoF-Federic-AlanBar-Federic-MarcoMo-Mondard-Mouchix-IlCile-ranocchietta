package frogger.model.implementations;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.LoadSave;
import frogger.model.interfaces.Button;

public class MenuButtons implements Button{

    private final int xPos, yPos, rowIndex;
    private int index;
    private BufferedImage[] imgs;
    private final GameState state;
    private boolean mousePressed, mouseOver;
    private Rectangle bounds;
    

    public MenuButtons(int xPos, int yPos, int rowIndex, GameState state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        this.rowIndex = rowIndex;
        loadImgs();
        initBounds();
    }
    
    private void loadImgs(){
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSprite(LoadSave.MENU_BUTTONS);
        for(int i = 0; i < imgs.length; i++){
            imgs[i] = temp.getSubimage(i * Constants.B_WIDTH_DEFAULT, rowIndex * Constants.B_HEIGHT_DEFAULT, Constants.B_WIDTH_DEFAULT, Constants.B_HEIGHT_DEFAULT);
        }
    }

    private void initBounds() {       
        bounds = new Rectangle(xPos- Constants.B_WIDTH/2, yPos, Constants.B_WIDTH, Constants.B_HEIGHT);
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(imgs[index], xPos - Constants.B_WIDTH/2, yPos, Constants.B_WIDTH, Constants.B_HEIGHT, null);
    }
    
    @Override
    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
    }

    @Override
    public boolean isMouseOver() {
        return mouseOver;
    }

    @Override
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    @Override
    public boolean isMousePressed() {
        return mousePressed;
    }

    @Override
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void applyGameState(){
        GameState.state = state;
    }

    @Override
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}