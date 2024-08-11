package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.scraps.Purchasable;

/**
 * An action that represents the purchase of a purchasable item.
 * Created by:
 * @author Jin Ruo Yew
 */
public class PurchaseAction extends Action {

    /**
     * The item that can be purchased.
     */
    private final Purchasable item;

    /**
     * Constructor.
     */
    public PurchaseAction(Purchasable item) {
        this.item = item;
    }

    /**
     * Executes the purchase of the item.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        double balance = actor.getBalance();

        if (balance >= item.getCost()) {
            return item.executePurchase(actor) + "\n" + getCurrentBalanceMessage(actor);
        } else {
            return "Insufficient funds to purchase " + item + "\n" + getCurrentBalanceMessage(actor);
        }
    }

    /**
     * Returns a string representing the current balance of the actor.
     * @param actor
     * @return a string representing the current balance of the actor.
     */
    public String getCurrentBalanceMessage(Actor actor){
        return "New balance: $" + actor.getBalance();
    }

    /**
     * Returns a description of the action to display in the menu.
     * @param actor The actor performing the action.
     * @return a string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Purchase " + item + " for $" + item.getCost();
    }

}
