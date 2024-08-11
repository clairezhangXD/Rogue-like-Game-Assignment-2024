package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * An abstract class for objects that are able to spawn Actors.
 * @see Actor
 * Created by:
 * @author Jin Ruo Yew
 */
public abstract class CreatureSpawner {

    /**
     * Abstract method to spawn a creature.
     */
    public abstract Actor spawn();

    /**
     * The spawn chance of the Creature.
     * @return the spawn chance of the Creature
     */
    public abstract double getSpawnChance();

    /**
     * Spawns a Creature at a random exit of the location.
     * @param location the location of the Floor that the HuntsmanSpider is spawned
     */
    public void spawnCreature(Location location) {
        if (Math.random() <= getSpawnChance()) {
            Random random = new Random();
            Location spawnLocation =
                    location.getExits()
                            .get(random.nextInt(location.getExits().size())).
                            getDestination();
            //spawns a Creature at a random exit of the location if no actor is present
            if ( spawnLocation.getActor() == null ) {
                spawnLocation.addActor(this.spawn());
            }
        }
    }
}
