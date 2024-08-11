package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawners.CreatureSpawner;

/**
 * A class that represents a type of Ground, crater that can spawn Creatures.
 * @see Ground
 * @see CreatureSpawner
 * @see game.actors.Creature
 * Created by:
 * @author Jin Ruo Yew
 */
public class Crater extends Ground {

    /**
     * The spawner that spawns a Creature.
     */
    private CreatureSpawner spawner;

    /**
     * Constructor.
     * @param spawner the spawner that spawns Creature
     */
    public Crater(CreatureSpawner spawner) {
        super('u');
        this.spawner = spawner;
    }

    /**
     * Tick method that spawns a Creature at the location of the Crater.
     * @param location the location of the Crater
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        spawner.spawnCreature(location);
    }

}
