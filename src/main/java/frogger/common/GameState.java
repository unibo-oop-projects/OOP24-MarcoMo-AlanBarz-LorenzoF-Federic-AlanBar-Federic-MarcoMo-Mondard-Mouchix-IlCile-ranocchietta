package frogger.common;

public enum GameState {
    
    PLAYING, SHOP, QUIT, MENU, DEAD, PAUSE;
    
    public static GameState state = MENU;
}