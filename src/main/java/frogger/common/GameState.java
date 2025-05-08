package frogger.common;

public enum GameState {
    
    MENU, PLAYING, SHOP, DEAD, QUIT,PAUSE;
    
    public static GameState state = MENU;
}