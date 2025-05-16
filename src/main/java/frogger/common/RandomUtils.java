package frogger.common;

import java.util.Random;

/**
 * Utility class for random position.
 */
public final class RandomUtils {

    private static final Random RAN = new Random();

    private RandomUtils() { }

    /**
     * Utility method.
     * @return a random x beetwen the min and max value of x
     */
    public static int randomX() {
        final int boundX = Math.abs(Constants.MAX_X) + Math.abs(Constants.MIN_X) + 1;
        final int deltaX = boundX - Math.abs(Constants.MAX_X);
        return RAN.nextInt(boundX) - deltaX;
    }

    /**
     * Utility method.
     * @return a random y beetwen the min and max value of y
     */
    public static int randomY() {
        final int boundY = Math.abs(Constants.MAX_Y) + Math.abs(Constants.MIN_Y) + 1;
        final int deltaY = boundY - Math.abs(Constants.MAX_Y);
        return RAN.nextInt(boundY) - deltaY;
    }
}
