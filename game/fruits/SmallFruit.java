package game.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;

/**
 * Class representing a small fruit that can be consumed by the player.
 * @see Consumable
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public class SmallFruit extends Fruit implements Consumable {

    /**
     * Constant storing the amount of hit points that the actor will be healed when the consumable item is consumed.
     */
    private static final int HIT_POINTS = 1;
    /**
     * Constructor.
     */
    public SmallFruit() {
        super("Small Fruit", 'o', true);
    }

    /**
     * Execute the action of consuming the small fruit by the actor and return the description of the result of the action.
     * @param actor who consumes the item
     * @return a string describing of what is consumed and what happen when the small fruit is consumed
     */
    @Override
    public String consumeBy(Actor actor){
        actor.heal(HIT_POINTS);
        actor.removeItemFromInventory(this);
        return "a small fruit. ";
    }

    /**
     * Get the list of allowable actions for the actor to perform on the consumable item.
     * @param owner the actor that is interacting with the consumable item
     * @return the list of allowable actions for the actor to perform with the consumable item
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        actions.add(new ConsumeAction(this));
        return actions;
    }

}
