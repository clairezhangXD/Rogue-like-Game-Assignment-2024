package game.scraps;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Utility;
import game.actions.TeleportAction;

public class Theseus extends Item implements Purchasable {

    /**
     * Cost of the theseus.
     */
    private int cost = 100;

    /**
     * constructor
     */
    public Theseus() {
        super("Theseus", '^', true);
    }

    /**
     * Get the cost of the theseus.
     * @return an integer representing the cost of the theseus.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Execute the action of purchasing the theseus by the actor.
     * @param actor the Actor that is purchasing the theseus.
     * @return a string representing the result of the purchase of the theseus.
     */
    @Override
    public String executePurchase(Actor actor) {
        String ret = "";
        actor.addItemToInventory(this);
        actor.deductBalance(cost);
        ret += "\n" + actor + " purchased " + this + " for $" + cost;

        return ret;
    }

    /**
     * actions that are available to the Player when the theseus is on the ground
     * @param location the location of the ground on which the theseus lies
     * @return the actions available to the Player when the theseus is on the ground
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        int randomXCoord = Utility.generateRandomInt(0, location.map().getXRange().max());
        int randomYCoord = Utility.generateRandomInt(0, location.map().getYRange().max());
        actions.add(new TeleportAction(location.map().at(randomXCoord,randomYCoord)));
        return actions;
    }
}
