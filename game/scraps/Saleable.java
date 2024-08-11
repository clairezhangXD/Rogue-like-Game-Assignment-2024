package game.scraps;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Creature;

/**
 * An interface that represents an item that can be sold by the player.
 * @see Actor
 * Created by:
 * @author Jin Ruo Yew
 */
public interface Saleable {

    /**
     * Returns the sale price of the item to be sold.
     * @return an integer representing the price of the item to be sold.
     */
    int getSalePrice();

    /**
     * Executes the sale of the item.
     * @param actor the Actor that is selling the item.
     * @param buyer the Actor that is buying the item.
     * @param map the GameMap where the sale is taking place.
     * @return a string describing the sale of the item.
     */
    String executeSale(Actor actor, Actor buyer, GameMap map);

}
