package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.actors.Creature;
import game.fruits.Consumable;

/**
 * Class representing a jar of pickles that can be consumed by the player. The jar of pickles has a 50% chance to be expired
 * and the player will get hurt by the specific hit point(s) if the expired jar of pickles is consumed,
 * otherwise the player will get healed by the specific hit point(s).
 *
 * @see Consumable
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public class JarOfPicklesItem extends Item implements Consumable, Saleable {

    /**
     * Constant storing the amount of hit points that the actor will be healed when the consumable item is consumed.
     */
    private static final int HIT_POINTS = 1;
    private static final double CHANCE = 0.5;
    private static final int SALE_PRICE = 25;
    private static final double SALE_RISK = 0.5;
    private static final int DOUBLE_SALE_PRICE = 2*SALE_PRICE;

    /***
     * Constructor.
     */
    public JarOfPicklesItem() {
        super("Jar Of Pickles", 'n', true);

    }

    /**
     * Returns the sale price of the Jar of Pickles item to be sold.
     * @return an integer representing the price of the Jar of Pickles item to be sold.
     */
    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Execute the action of consuming the jar of pickles by the actor and return the description of the result of the action.
     * @param actor who consumes the object
     * @return a string describing of what is consumed and what happen when the jar of pickles is consumed
     */
    @Override
    public String consumeBy(Actor actor){
        String description;
        if (Math.random() <= CHANCE) {
            actor.hurt(HIT_POINTS);
            description = "an expired Jar of Pickles. " + actor + " feels sick.";
        } else {
            actor.heal(HIT_POINTS);
            description = "a fresh Jar of Pickles. " ;
        }
        actor.removeItemFromInventory(this);
        return description;
    }

    /**
     * Executes the sale of the Jar of Pickles item to the buyer.
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
            ret += buyer + " paid double!\n";
            actor.addBalance(DOUBLE_SALE_PRICE);
            ret += actor + " sold a " + this + " for $" + DOUBLE_SALE_PRICE;
        }
        else {
            actor.addBalance(SALE_PRICE);
            ret += actor + " sold a " + this + " for $" + SALE_PRICE;
        }

        return ret;
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

    /**
     * List of allowable actions that the item allows its owner do to other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability(Ability.PURCHASE_ITEMS)){
            actions.add(new SellAction(this, otherActor));
        }
        return actions;
    }

}
