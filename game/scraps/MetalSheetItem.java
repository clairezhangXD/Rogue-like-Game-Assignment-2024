package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.SellAction;
import game.actors.Creature;

/**
 * MetalSheetItem class that represents a metal sheet item that can be sold by Actor.
 * @see Item
 * @see Actor
 * Created by:
 * @author Jin Ruo Yew
 */
public class MetalSheetItem extends Item implements Saleable {

    /**
     * Constant storing the sale price of the metal sheet.
     */
    private static final int SALE_PRICE = 20;
    private static final int DISCOUNT_SALE_PRICE = 10;
    private static final double SALE_RISK = 0.6;

    /**
     * Constructor.
     */
    public MetalSheetItem() {
        super("Metal Sheet", '%', true);
    }

    /**
     * Returns the sale price of the Metal Sheet item to be sold.
     * @return an integer representing the price of the Metal Sheet item to be sold.
     */
    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Executes the sale of the Metal Sheet item to the buyer.
     * @param actor the Actor that is selling the item.
     * @param buyer the Actor that is buying the item.
     * @param map the GameMap where the sale is taking place.
     * @return a string describing the sale of the item.
     */
    @Override
    public String executeSale(Actor actor, Actor buyer, GameMap map){
        String ret = "\n";

        actor.removeItemFromInventory(this);
        if (Math.random() < SALE_RISK){
            ret += buyer + " asked for a discount!\n";
            actor.addBalance(DISCOUNT_SALE_PRICE);
            ret += actor + " sold a " + this + " for $" + DISCOUNT_SALE_PRICE;
        }
        else {
            actor.addBalance(SALE_PRICE);
            ret += actor + "sold a " + this + " for $" + SALE_PRICE;
        }

        return ret;
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
