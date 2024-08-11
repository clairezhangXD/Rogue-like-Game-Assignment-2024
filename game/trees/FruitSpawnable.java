package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.fruits.Consumable;
import game.fruits.Fruit;
import game.utilities.SelectRandomLocation;

/**
 * An interface for objects that are able to spawn Fruits.
 * Provides the functionality to spawn fruit at a random location within a specified area.
 * This interface defines a default method to handle the spawning process.
 * @see Consumable
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public interface FruitSpawnable {

    /**
     * Spawns a specified type of fruit at a random selected exit where the tree located at.
     * @param location The current location of the object
     * @param fruit The fruit to be spawned
     */
    default void spawnFruit(Location location,Fruit fruit) {
            SelectRandomLocation.selectRandomLocation(location).addItem(fruit);
    }
}