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
 * Class representing a pot of gold that can be picked up, consumed and sold by the player.
 * The intern can take out the gold from the pot and put it in their wallet which will increase their wallet amount by the specific credit points.
 * @see Consumable
 * Created by:
 * @author Chloe Ang
 */
public class PotOfGoldItem extends Item implements Consumable, Saleable {

    /**
     * Constant storing the sale price of the pot of gold.
     */
    private final static int SALE_PRICE = 500;

    private final static int CREDIT_POINTS = 10;
    private final static double SALE_RISK = 0.25;
    private final static int DISCOUNT_SALE_PRICE = 0;

    /***
     * Constructor.
     */
    public PotOfGoldItem() {
        super("Pot Of Gold", '$', true);
    }

    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Execute the action of consuming the Pot of Gold by the actor and return the description of the result of the action.
     * @param actor who consumes the object
     * @return a string describing of what is consumed and what happen when the pot of gold is consumed
     */
    @Override
    public String consumeBy(Actor actor){
        actor.addBalance(CREDIT_POINTS);
        actor.removeItemFromInventory(this);
        return actor + " empties out "+ this + " and gains "+ CREDIT_POINTS + " credits." +
                "The rest are withheld as tax by the factory.";
    }

    /**
     * Execute the action of selling the Pot of Gold by the actor to the buyer.
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
            ret += buyer + " snatched item without paying!\n";
            actor.addBalance(DISCOUNT_SALE_PRICE);
            ret += actor + " sold a " + this + " for $" + DISCOUNT_SALE_PRICE;
        }
        else {
            actor.addBalance(SALE_PRICE);
            ret += actor + " sold a " + this + " for $" + SALE_PRICE;
        }

        return ret;
    }

    /**
     * Get the list of allowable actions for the actor to perform on the consumable item.
     * @param owner the actor that owns the item
     * @return a list of actions that are allowed to perform on this object
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
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability(Ability.PURCHASE_ITEMS)){
            actions.add(new SellAction(this, otherActor));
        }
        return actions;
    }

}
