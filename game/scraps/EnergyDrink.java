package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.fruits.Consumable;

/**
 * A class that represents an energy drink item that can be purchased and consumed by the Player.
 * @see Item
 * @see Consumable
 * @see Purchasable
 * Created by:
 * @author Jin Ruo Yew
 */
public class EnergyDrink extends Item implements Consumable, Purchasable{

    /**
     * Constant hit points for the energy drink item when consumed.
     */
    private static final int HIT_POINTS = 1;

    /**
     * Cost of the energy drink item.
     */
    private int cost = 10;

    /**
     * Constant risk chance of the energy drink item.
     */
    private static final double RISK_CHANCE = 0.2;


    /**
     * Constructor.
     */
    public EnergyDrink() {
        super("Energy Drink", '*', true);
    }

    /**
     * Get the hit points of the energy drink item.
     * @return an integer representing the hit points of the energy drink item.
     */
    public int getHitPoints() {
        return HIT_POINTS;
    }

    /**
     * Get the cost of the energy drink item.
     * @return an integer representing the cost of the energy drink item.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Execute the action of purchasing the energy drink item by the actor.
     * @param actor the Actor that is purchasing the item.
     * @return a string representing the result of the purchase of the energy drink item.
     */
    @Override
    public String executePurchase(Actor actor) {
        String ret = "";
        if (Math.random() < RISK_CHANCE) {
            ret += "Oh no, " + this + " price doubled!";
            this.cost = 2*this.cost;

            if (this.cost > actor.getBalance()){
                ret += "\nInsufficient funds to purchase " + this;
            }
            else {
                actor.addItemToInventory(this);
                actor.deductBalance(cost);
                ret += "\n" + actor + " purchased " + this + " for $" + cost;
            }

        } else {
            actor.addItemToInventory(this);
            actor.deductBalance(cost);
            ret += "\n" + actor + " purchased " + this + " for $" + cost;
        }

        return ret;
    }

    /**
     * Consume the energy drink item to heal the actor.
     * @param actor
     * @return a string representing the result of consuming the energy drink item.
     */
    public String consumeBy(Actor actor) {
        actor.heal(HIT_POINTS);
        return "an energy drink.";
    }

    /**
     * Get the allowable actions for the energy drink item.
     * @param owner the actor that is interacting with the consumable item
     * @return an ActionList containing the allowable actions for the energy drink item.
     */
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        actions.add(new ConsumeAction(this));
        return actions;
    }

}
