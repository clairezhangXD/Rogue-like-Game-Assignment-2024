package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;

import java.util.Map;
import java.util.TreeMap;

/**
 *  Abstract class representing a Creature in the game environment.
 *  Creatures are specialised types of Actors with specific behaviours and capabilities
 *  The class extends the {@link Actor} class
 *  Each creature possesses a set of behaviors that dictate its actions during its turn in the gameplay.
 *  Behaviors are prioritized and managed through a TreeMap, allowing the creature to select actions dynamically.
 */
public abstract class Creature extends Actor {
    /**
     * A map that stores the behaviours of the creature.
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * The constructor of the Creature class.
     *
     * @param name        the name of the Creature
     * @param displayChar the character that will represent the Creature in the display
     * @param hitPoints   the Creature's starting hit points
     */
    public Creature(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * At each turn, select a valid action to perform
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The creature can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
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
        }
        return actions;
    }

    /**
     * Adding behaviours to the creature's TreeMap of behaviours
     *
     * @param priority      The priority of the behaviour being added (lower = higher priority)
     * @param newBehaviour  The behaviour being added
     */
    protected void addBehaviour(int priority, Behaviour newBehaviour){
        this.behaviours.put(priority, newBehaviour);
    }
}
