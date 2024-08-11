package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.Status;

/**
 * Class representing the behaviour of a creature actor attacking another actor.
 * @see Actor
 * @see Behaviour
 * Created by:
 * @author Jin Ruo Yew
 */
public class CreatureAttackBehaviour implements Behaviour {

    private final String VERB = "hits";
    private int damage;
    private int hitRate;

    public CreatureAttackBehaviour(int hitRate, int damage){
        this.hitRate = hitRate;
        this.damage = damage;
    }

    /**
     * Get the action of the creature attacking another actor.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return the action of the creature attacking another creature
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(target, exit.getName(), new IntrinsicWeapon(damage, VERB, hitRate));
                }
            }
        }
        return null;
    }

}

