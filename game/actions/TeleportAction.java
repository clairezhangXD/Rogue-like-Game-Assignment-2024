package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action that teleports the actor
 */
public class TeleportAction extends Action {
    private final Location teleportToLocation;

    /**
     * constructor
     * @param teleportToLocation teleport to this location
     */
    public TeleportAction(Location teleportToLocation){
        this.teleportToLocation = teleportToLocation;
    }

    /**
     * Returns a description of this teleportation to display in the menu.
     * @param actor The actor performing the action.
     * @return description of this teleportation
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to random location";
    }

    /**
     * Allows the Actor to be teleported
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return fail message if an actor is already present at the location to be teleported to,
     * otherwise the menuDescription
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(map.isAnActorAt(teleportToLocation)) {
            return "Teleport failure! An actor is in the way.";
        }
        else {
            map.moveActor(actor, teleportToLocation);
            return menuDescription(actor);
        }
    }
}
