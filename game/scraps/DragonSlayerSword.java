package game.scraps;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actions.AttackAction;

/**
 * A class that represents a DragonSlayerSword WeaponItem that can be purchased by the Player.
 * @see WeaponItem
 * @see Purchasable
 * Created by:
 * @author Jin Ruo Yew
 */
public class DragonSlayerSword extends WeaponItem implements Purchasable {

    /**
     * Constant damage that the DragonSlayerSword can deal.
     */
    private static final int DAMAGE = 50;

    /**
     * Cost of the DragonSlayerSword.
     */
    private int cost = 100;

    /**
     * Constant verb that the DragonSlayerSword uses to attack.
     */
    private static final String VERB = "slashes";

    /**
     * Constant hit rate of the DragonSlayerSword.
     */
    private static final int HIT_RATE = 75;

    /**
     * Constant risk chance of the DragonSlayerSword.
     */
    private static final double RISK_CHANCE = 0.5;

    /**
     * Constructor.
     */
    public DragonSlayerSword() {
        super("Dragon Slayer Sword", 'x', DAMAGE, VERB, HIT_RATE);
    }

    /**
     * Get the cost of the DragonSlayerSword.
     * @return an integer representing the cost of the DragonSlayerSword.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Execute the action of purchasing the DragonSlayerSword by the actor.
     * @param actor the Actor that is purchasing the item.
     * @return a string representing the result of the purchase of the DragonSlayerSword.
     */
    @Override
    public String executePurchase(Actor actor) {
        String ret = "";

        if (Math.random() < RISK_CHANCE) {
            ret += "Oh no, terminal malfunction! Credits are wiped!";
        }
        else {
            actor.addItemToInventory(this);
        }

        actor.deductBalance(cost);
        ret += "\n" + actor + " purchased " + this + " for $" + cost;

        return ret;
    }

    /**
     * List of allowable actions that the actor which has the DragonSlayerSword can perform on the other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(location);
        if (otherActor.hasCapability(Status.INDIFFERENT_TO_PLAYER) || otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }

}
