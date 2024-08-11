package game.fruits;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface implemented by object that can be consumed by an actor. The methods defined in this class must be implemented by
 * all the consumable object. When an item implementing this interface is consumed, the method is called
 * to execute the consumption logic, returning a description of both the item consumed and the outcome of the action.
 *
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public interface Consumable {

    /**
     * Execute the action of consuming the object by the actor and return the description of the result of the action
     * @param actor who consumes the object
     * @return a string describing of what is consumed and what happen when the object is consumed
     */
    String consumeBy(Actor actor);

}
