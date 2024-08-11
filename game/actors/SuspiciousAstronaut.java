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
import game.behaviours.CreatureAttackBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class that represents a Suspicious Astronaut creature actor in the game,
 * which attacks and kills the Player when the Player is 1 exit away.
 * @see Actor
 * Created by:
 * @author Anh Nguyen
 */
public class SuspiciousAstronaut extends Creature {

    private static final int HIT_RATE = 100;
    private static final int DAMAGE = 99999;

    /**
     * Constructor, initialised using the super class's constructor.
     * The Suspicious Astronaut is represented by the character 'ඞ' and has 99 hit points.
     * The Suspicious Astronaut has 2 behaviours: WanderBehaviour and CreatureAttackBehaviour.
     *
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new CreatureAttackBehaviour(HIT_RATE, DAMAGE));
    }
}
