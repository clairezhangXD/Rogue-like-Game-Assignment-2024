package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.SellAction;
import game.actors.Creature;

/**
 * LargeBoltItem class that represents a large bolt item.
 * @see Item
 * Created by:
 * @author Jin Ruo Yew
 */
public class LargeBoltItem extends Item implements Saleable {

    /**
     * Constant storing the sale price of the large bolt.
     */
    private static final int SALE_PRICE = 25;

    /**
     * Constructor.
     */
    public LargeBoltItem(){
        super("Large Bolt", '+', true);
    }

    /**
     * Returns the sale price of the large bolt item to be sold.
     * @return an integer representing the price of the large bolt item to be sold.
     */
    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Executes the sale of the Large Bolt item to the buyer.
     * @param actor the Actor that is selling the item.
     * @param buyer the Actor that is buying the item.
     * @param map the GameMap where the sale is taking place.
     * @return a string describing the sale of the item.
     */
    @Override
    public String executeSale(Actor actor, Actor buyer, GameMap map){
        actor.removeItemFromInventory(this);
        actor.addBalance(SALE_PRICE);
        return "\n" + actor + " sold a " + this + " for $" + SALE_PRICE;
    }

    /**
     * List of allowable actions that the item allows its owner do to other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability(Ability.PURCHASE_ITEMS)){
            actions.add(new SellAction(this, otherActor));
        }
        return actions;
    }


}
