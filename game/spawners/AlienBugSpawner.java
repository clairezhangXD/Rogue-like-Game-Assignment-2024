package game.spawners;
import edu.monash.fit2099.engine.actors.Actor;
import game.actors.AlienBug;

public class AlienBugSpawner extends CreatureSpawner {
    /**
     * Constant storing the chance of spawning an Alien Bug.
     */
    private static final double SPAWN_CHANCE = 0.1;

    /**
     * Spawns an Alien Bug.
     * @return an Alien Bug.
     */
    public Actor spawn() {
        return new AlienBug();
    }

    /**
     * The spawn chance of the Alien Bug.
     * @return the spawn chance of the Alien Bug
     */
    @Override
    public double getSpawnChance() {
        return SPAWN_CHANCE;
    }
}
