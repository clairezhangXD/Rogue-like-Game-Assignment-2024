package game.fruits;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.actors.Creature;
import game.scraps.Saleable;

/**
 * Class representing a large fruit that can be consumed by the player.
 * @see Consumable
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public class LargeFruit extends Fruit implements Consumable, Saleable {

    /**
     * Constant storing the amount of hit points that the actor will be healed when the consumable item is consumed.
     */
    private static final int HIT_POINTS = 2;

    /**
     * Constant storing the sale price of the large fruit.
     */
    private static final int SALE_PRICE = 30;

    /**
     * Constructor.
     */
    public LargeFruit() {
        super("Large Fruit",'O', true);
    }

    /**
     * Returns the sale price of the large fruit item to be sold.
     * @return an integer representing the price of the large fruit item to be sold.
     */
    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Execute the action of consuming the large fruit by the actor and return the description of the result of the action
     * @param actor who consumes the object
     * @return a string describing of what is consumed and what happen when the large fruit is consumed
     *
     */
    @Override
    public String consumeBy(Actor actor){
        actor.heal(HIT_POINTS);
        actor.removeItemFromInventory(this);
        return "a large fruit.";
    }

    /**
     * Execute the action of selling the large fruit by the actor to the buyer.
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
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability(Ability.PURCHASE_ITEMS)){
            actions.add(new SellAction(this, otherActor));
        }
        return actions;
    }

}
