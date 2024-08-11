package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Ability;
import game.Status;
import game.actions.AttackAction;
import game.actions.SellAction;

/**
 * A class that represents a Metal Pipe Item which is a special Item that can be used by the actor as a weapon.
 * @see WeaponItem
 * Created by:
 * @author Jin Ruo Yew
 */
public class MetalPipeItem extends WeaponItem implements Saleable {

    /**
     * Constant damage that the MetalPipeItem can deal.
     */
    private static final int DAMAGE = 1;

    /**
     * Constant verb that the MetalPipeItem uses to attack.
     */
    private static final String VERB = "whacks";

    /**
     * Constant hit rate of the MetalPipeItem.
     */
    private static final int HIT_RATE = 20;

    /**
     * Constant sale price of the MetalPipeItem.
     */
    private static final int SALE_PRICE = 35;

    /**
     * Constructor.
     */
    public MetalPipeItem() {
        super("Metal Pipe", '!', DAMAGE, VERB, HIT_RATE);
    }

    /**
     * Returns the sale price of the Metal Pipe Item to be sold.
     * @return an integer representing the price of the Metal Pipe Item to be sold.
     */
    @Override
    public int getSalePrice() {
        return SALE_PRICE;
    }

    /**
     * Executes the sale of the Metal Pipe Item to the buyer.
     * @param actor the Actor that is selling the item.
     * @param buyer the Actor that is buying the item.
     * @param map the GameMap where the sale is taking place.
     * @return a string describing the sale of the item.
     */
    @Override
    public String executeSale(Actor actor, Actor buyer, GameMap map){
        actor.removeItemFromInventory(this);
        actor.addBalance(SALE_PRICE);
        return "\n" + actor + "sold a " + this + " for $" + SALE_PRICE;
    }

    /**
     * List of allowable actions that the actor which has the MetalPipeItem can perform on the other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(location);
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER) || otherActor.hasCapability(Status.INDIFFERENT_TO_PLAYER)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        if (otherActor.hasCapability(Ability.PURCHASE_ITEMS)){
            actions.add(new SellAction(this, otherActor));
        }
        return actions;
    }



}
