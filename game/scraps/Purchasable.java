package game.scraps;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Interface for purchasable items.
 * @see Item
 * Created by:
 * @author Jin Ruo Yew
 */
public interface Purchasable {

    /**
     * Get the cost of the purchasable item.
     * @return an integer representing the cost of the purchasable item.
     */
    int getCost();

    /**
     * Executes the purchase of the item.
     * @param actor the Actor that is purchasing the item.
     * @return a string describing the purchase of the item.
     */
    String executePurchase(Actor actor);


}
