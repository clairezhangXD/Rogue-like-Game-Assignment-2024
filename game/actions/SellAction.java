package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Creature;
import game.scraps.Saleable;

/**
 * An action that represents the sale of a saleable item.
 * Created by:
 * @author Jin Ruo Yew
 */
public class SellAction extends Action {

    /**
     * The item that can be sold.
     */
    private final Saleable item;
    private final Actor buyer;

    /**
     * Constructor.
     */
    public SellAction(Saleable item, Actor buyer) {
        this.item = item;
        this.buyer = buyer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " attempts to sell " + item + " for " + item.getSalePrice() +
                item.executeSale(actor, this.buyer, map);
    }

    /**
     * Returns a description of the action to display in the menu.
     * @param actor The actor performing the action.
     * @return a string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sell " + item + " to " + this.buyer + " for $" + item.getSalePrice();
    }

}
