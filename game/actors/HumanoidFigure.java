package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.actions.AttackAction;
import game.behaviours.CreatureAttackBehaviour;

/**
 * A class that represents a Humanoid Figure Actor that can purchase items.
 * @see Actor
 * Created by:
 * @author Jin Ruo Yew
 */
public class HumanoidFigure extends Actor {

    /**
     * Constant storing the name and display character of the Humanoid Figure.
     */
    private static final String NAME = "Humanoid Figure";

    /**
     * Constant storing the display character of the Humanoid Figure.
     */
    private static final char DISPLAY_CHAR = 'H';

    /**
     * Constructor.
     */
    public HumanoidFigure(){
        super(NAME, DISPLAY_CHAR, 100);
        // Add the ability to purchase items
        capabilitySet.addCapability(Ability.PURCHASE_ITEMS);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an Action that the otherActor can do, or null if no possible Actions.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
