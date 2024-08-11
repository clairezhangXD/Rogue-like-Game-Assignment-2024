package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a wall.
 * Created by:
 * @author Riordan D. Alfredo
 *
 */
public class Wall extends Ground {

    /**
     * Constructor.
     */
    public Wall() {
        super('#');
    }

    /**
     * A method that returns false as an Actor cannot enter a Wall.
     * @param actor the Actor that wants to enter the Wall
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
