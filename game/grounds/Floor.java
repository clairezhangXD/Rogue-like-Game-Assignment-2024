package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 */
public class Floor extends Ground {

    /**
     * Constructor.
     */
    public Floor() {
        super('_');
    }

    /**
     * A method that returns true if an actor can enter a Floor.
     * @param actor the Actor that wants to enter the Floor
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.HOSTILE_TO_PLAYER);
    }
}
