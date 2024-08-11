package game.utilities;

import java.util.Random;
/**
 * Tool for any class
 */
public class Utility {
    /**
     * Generates a random integer number between lower and upper
     * @param lower the integer minimum
     * @param upper the integer maximum
     * @return random integer
     */
    public static int generateRandomInt(int lower, int upper) {
        Random random = new Random();
        return random.nextInt(upper - lower + 1) + lower;
    }

}
