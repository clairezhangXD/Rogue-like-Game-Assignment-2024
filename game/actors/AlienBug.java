package game.actors;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.utilities.Utility;
import game.actions.AttackAction;
import game.behaviours.FollowingBehaviour;
import game.behaviours.StealBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * A class that represents an AlienBug creature actor in the game,
 * which steals items on the map and follows the Player, until it falls unconscious,
 * when the Player stands 1 exit away.
 */
public class AlienBug extends Creature {

     /**
     * Constructor, initialised using the super class's constructor.
     * The AlienBug is represented by the character 'a' and has 2 hit points.
     * The AlienBug has 2 behaviours: WanderBehaviour and StealBehaviour.
     */
    public AlienBug() {
        super("Feature-" + Utility.generateRandomInt(100, 999), 'a', 2);
        this.addCapability(Status.INDIFFERENT_TO_PLAYER);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new StealBehaviour());
    }

    /**
     * The AlienBug can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     * and can follow them
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that can be performed by the Actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            this.addBehaviour(2, new FollowingBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Method that can be executed when the AlienBug is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map where the AlienBug fell unconscious
     * @return a string describing what happened when the AlienBug is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        for (Item item : this.getItemInventory()){
            map.locationOf(this).addItem(item);
        }
        map.removeActor(this);
        return this + " met their demise at the hand of " + actor;
    }
}