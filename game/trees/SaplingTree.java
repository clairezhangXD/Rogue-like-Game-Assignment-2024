package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.fruits.Fruit;
import game.fruits.SmallFruit;

/**
 * A class that represents a type of Inheritree, SaplingTree.
 * This tree will grow into a Young Tree after reaching its maximum age of 6 ticks and can spawn fruit with 0.3 chance.
 * @see Inheritree
 * @see FruitSpawnable
 * @see SmallFruit
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 *
 */
public class SaplingTree extends Inheritree implements FruitSpawnable {


    private static final double SPAWN_CHANCE = 0.3 ;
    private static final int MAX_AGE = 6 ;
    private TreeFactory factory;

    /**
     * Construct a Sapling Tree,this tree grows into a Young Tree once it reaches its maximum age.
     */
    public SaplingTree(TreeFactory factory){
        super('t', factory,MAX_AGE);
        this.factory = factory;
    }

    /**
     * Processes a single tick for the tree.
     * The tree's age is incremented each tick, it will grow into next stage once it reaches its maximum age.
     * If the random chance conditions are met, the tree will spawn a small fruit at a selected location.
     *
     * @param location the location of the tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // Only spawn fruit if it's not transitioning this tick
        if (Math.random()<= SPAWN_CHANCE && this.getAge() < this.getMaxAge()) {
            Fruit fruit = new SmallFruit();
            spawnFruit(location, fruit);
        }
    }

    /**
     * Creates the next stage of the tree, which is a Young Tree.
     *
     * @return the next stage of the tree
     */
    @Override
    public Inheritree createNextStage() {
        return factory.createYoungTree();
    }
}
