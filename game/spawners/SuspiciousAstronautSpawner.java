package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.SuspiciousAstronaut;

public class SuspiciousAstronautSpawner extends CreatureSpawner{
    /**
     * Constant storing the chance of spawning a SuspiciousAstronaut.
     */
    private static final double SPAWN_CHANCE = 0.05;

    /**
     * Spawns a SuspiciousAstronaut.
     * @return a SuspiciousAstronaut
     */
    @Override
    public Actor spawn() {
        return new SuspiciousAstronaut();
    }

    /**
     * The spawn chance of the SuspiciousAstronaut.
     * @return the spawn chance of the SuspiciousAstronaut
     */
    @Override
    public double getSpawnChance() {
        return SPAWN_CHANCE;
    }
}
