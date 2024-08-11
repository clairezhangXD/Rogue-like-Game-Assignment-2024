package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.fruits.Consumable;

/**
 * An action that represents consuming a Consumable item,
 * which handles consumption logic which varies with different item.
 * It extends the Action class to provide specific behavior for consumable items in a game environment.
 * @see Action
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public class ConsumeAction extends Action {

    /**
     * The Consumable to be consumed.
     */
    private Consumable consumable;

    /**
     * Constructor.
     * @param consumable the Consumable item to be consumed by the actor
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Perform the action of consuming the consumable item,
     * this method constructs a string that describes the action of the actor consuming the item.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action performed
     */
    public String execute(Actor actor, GameMap map) {
        return actor + " consumes " + consumable.consumeBy(actor);
    }

    /**
     * Describe what action will be performed if this ConsumeAction is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable.toString();
    }
}
