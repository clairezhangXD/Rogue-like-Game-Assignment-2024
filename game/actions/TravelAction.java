package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action that allows the actor to travel to other maps
 */
public class TravelAction extends Action {

    private final Location travelToLocation;
    private final String mapName;

    /**
     * constructor
     * @param travelToLocation location to travel to
     * @param mapName name of the map to travel to
     */
    public TravelAction(Location travelToLocation, String mapName) {
        this.travelToLocation = travelToLocation;
        this.mapName = mapName;

    }

    /**
     * Returns a description of travelling to another map to display in the menu.
     * @param actor The actor performing the action.
     * @return description of this travel
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + mapName;
    }

    /**
     * Allows the Actor to travel to other maps
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return fail message if an actor is already present at the location to travel to,
     * otherwise the menuDescription
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(map.isAnActorAt(travelToLocation)) {
            return "Travel failure! An actor is in the way.";
        }
        else {
            map.moveActor(actor, travelToLocation);
            return menuDescription(actor);
        }
    }
}
