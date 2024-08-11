package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.SellAction;
import game.actors.Creature;

/**
 * A class that represents a toilet paper roll Item that can be purchased and sold by the player.
 * @see Item
 * Created by:
 * @author Jin Ruo Yew
 */
public class ToiletPaperRoll extends Item implements Purchasable, Saleable {

    /**
     * Cost of the toilet paper roll.
     */
    private int cost = 5;

    /**
     * Constant risk chance of the toilet paper roll.
     */
    private static final double RISK_CHANCE = 0.75;

    /**
     * The discounted price of the item.
     */
    private static final int DISCOUNT_PRICE = 1;

    /**
     * Constant storing the sale price of the toilet paper roll.
     */
    private static final int SALE_PRICE = 1;
    private static final double SALE_RISK = 0.5;

    /**
     * Constructor.
     */
    public ToiletPaperRoll() {
        super("Toilet Paper Roll", 's', true);
    }

    /**
     * Get the cost of the toilet paper roll.
     * @return an integer representing the cost of the toilet paper roll.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Get the sale price of the toilet paper roll.
     * @return an integer representing the sale price of the toilet paper roll.
     */
    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Execute the action of purchasing the toilet paper roll by the actor.
     * @param actor the Actor that is purchasing the toilet paper roll.
     * @return a string representing the result of the purchase of the toilet paper roll.
     */
    public String executePurchase(Actor actor) {
        String ret = "";
        actor.addItemToInventory(this);

        if (Math.random() < RISK_CHANCE){
            ret += "\nLucky day, " + this + " price discount!";
            this.cost = DISCOUNT_PRICE;
        }
        actor.deductBalance(cost);
        ret += "\n" + actor + " bought a " + this + " for $" + cost;

        return ret;
    }

    /**
     * Execute the action of selling a Toilet Paper Roll to the buyer.
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
            actor.hurt(Integer.MAX_VALUE);
            ret += buyer + " hurt " + actor + "!\n";
            ret += actor.unconscious(buyer, map);
        }
        else {
            actor.addBalance(SALE_PRICE);
            ret += actor + " sold a " + this + " for $" + SALE_PRICE;
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
