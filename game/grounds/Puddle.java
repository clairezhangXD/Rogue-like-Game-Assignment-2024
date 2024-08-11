package game.grounds;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.fruits.Consumable;

/**
 * A class that represents a puddle of water.
 * The Intern can drink the water directly from the puddle of water without adding it to the inventory.
 * Drinking the water will increase the intern’s maximum health by 1 point permanently.
 * @author Chloe Ang
 */
public class Puddle extends Ground implements Consumable {

    private static final int POINT = 1;
    /**
     * Constructor.
     */
    public Puddle() {
        super('~');
    }

    /**
     * Returns a string representation of this ground object
     * @return a fixed descriptive string of this object
     */
    @Override
    public String toString() {
        return "a random puddle of water.";
    }

    /**
     * Execute the action of consuming the puddle of water by the actor and return the description of the result of the action
     * @param actor who consumes the item
     * @return a string describing of what is consumed and what happen when the puddle of water is consumed
     */
    @Override
    public String consumeBy(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,POINT);
        return toString()+" Actor's maximum hit point increases by 1.";
    }

    /**
     * Get the list of allowable actions for the actor to perform on the ground.
     * @param actor the actor that is interacting with the consumable item
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the list of allowable actions for the actor to perform with the consumable item
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor,location,direction);
        if (location.containsAnActor()) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
