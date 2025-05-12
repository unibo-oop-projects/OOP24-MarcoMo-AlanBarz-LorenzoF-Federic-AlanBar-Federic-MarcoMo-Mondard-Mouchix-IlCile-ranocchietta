package frogger.common;

import java.util.Random;

public class RandomUtils {

    private static final Random ran = new Random();

    /**
     * Utility method
     * @return a random x beetwen the min and max value of x
     */
    public static int randomX() {
        int boundX = Math.abs(Constants.MAX_X) + Math.abs(Constants.MIN_X) + 1;
        int deltaX = boundX - Math.abs(Constants.MAX_X);
        return ran.nextInt(boundX) - deltaX;
    }

    /**
     * Utility method
     * @return a random y beetwen the min and max value of y
     */
    public static int randomY() {
        int boundY = Math.abs(Constants.MAX_Y) + Math.abs(Constants.MIN_Y) + 1;
        int deltaY = boundY - Math.abs(Constants.MAX_Y);
        return ran.nextInt(boundY) - deltaY;
    }
}
