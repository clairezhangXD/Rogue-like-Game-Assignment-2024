package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.HuntsmanSpider;

/**
 * A class that represents a spawner that spawns HuntsmanSpiders.
 * @see CreatureSpawner
 * @see HuntsmanSpider
 * Created by:
 * @author Jin Ruo Yew
 */
public class HuntsmanSpiderSpawner extends CreatureSpawner {

    /**
     * Constant storing the chance of spawning a HuntsmanSpider.
     */
    private static final double SPAWN_CHANCE = 0.05;

    /**
     * Spawns a HuntsmanSpider.
     * @return a HuntsmanSpider
     */
    @Override
    public Actor spawn() {
        return new HuntsmanSpider();
    }

    /**
     * The spawn chance of the HuntsmanSpider.
     * @return the spawn chance of the HuntsmanSpider
     */
    @Override
    public double getSpawnChance() {
        return SPAWN_CHANCE;
    }
}