package game.utilities;

import edu.monash.fit2099.engine.positions.Location;
import java.util.Random;

/**
 * This class provides utility functions for selecting a random location based on the exits of a given location.
 * Created by:
 * @author Chloe Ang
 */
public class SelectRandomLocation {

    /**
     * Selects a random destination from the available exits of the specified location.
     * It randomly selects one of these exits and returns the destination of that exit.
     *
     * @param location The current location of the object with potential exits.
     * @return A randomly selected destination location derived from one of the exits of the provided location.
     */
    public static Location selectRandomLocation(Location location) {
        Random random = new Random();
        Location randomLocation = location.getExits()
                .get(random.nextInt(location.getExits().size()))
                .getDestination();
        return randomLocation;
    }
}
