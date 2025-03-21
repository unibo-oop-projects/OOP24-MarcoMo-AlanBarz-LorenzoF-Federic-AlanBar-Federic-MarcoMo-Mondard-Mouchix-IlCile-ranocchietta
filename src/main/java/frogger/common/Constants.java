package frogger.common;

public class Constants {
    public final static int MIN_OBSTACLES_NUMBER = 2;
    public final static int MAX_OBSTACLES_NUMBER = 3;

    public final static int MIN_CAR_WIDTH = 1;
    public final static int MAX_CAR_WIDTH = 1;

    public final static int MIN_TRUNK_WIDTH = 1;
    public final static int MAX_TRUNK_WIDTH = 1;

    public final static int OBJECT_HEIGHT = 1;

    public final static int LANE_HEIGHT = 1;

    public final static int MAX_X = 6;
    public final static int MIN_X = -7;
    public final static int MAX_Y = 6;
    public final static int MIN_Y = -6;
    public final static int ROAD_LANES = 5;
    public final static int RIVER_LANES = 5;
    public final static int N_ROW = ROAD_LANES + RIVER_LANES + 3;
    public final static int N_COLUMN = Math.abs(MAX_X) + Math.abs(MIN_X) + 1;

    public static int FRAME_WIDTH = 900;
    public static int FRAME_HEIGHT = 800;
    
    public static int BLOCK_WIDTH = FRAME_WIDTH / N_COLUMN;
    public static int BLOCK_HEIGHT = FRAME_HEIGHT / N_ROW;

    public static int PLAYER_WIDTH = 56;
    public static int PLAYER_HEIGHT = 56;
}