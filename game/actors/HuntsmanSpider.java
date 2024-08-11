package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.CreatureAttackBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class that represents a Huntsman Spider creature actor in the game,
 * which attacks the Player when the Player is 1 exit away.
 * @see Actor
 * Created by:
 * @author Jin Ruo Yew
 */
public class HuntsmanSpider extends Creature {

    private static final int HIT_RATE = 25;
    private static final int DAMAGE = 1;

    /**
     * Constructor, initialised using the super class's constructor.
     * The HuntsmanSpider is represented by the character '8' and has 1 hit point.
     * The HuntsmanSpider has 2 behaviours: WanderBehaviour and CreatureAttackBehaviour.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new CreatureAttackBehaviour(HIT_RATE, DAMAGE));
    }
}
