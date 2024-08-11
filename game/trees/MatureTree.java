package game.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.fruits.Fruit;
import game.fruits.LargeFruit;

/**
 * A class that represents a type of Inheritree, MatureTree.
 * This tree is not further growing into any other type of tree but can spawn fruit with 0.2 chance.
 * @see Inheritree
 * @see FruitSpawnable
 * @see LargeFruit
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public class MatureTree extends Inheritree implements FruitSpawnable{


    private static final double SPAWN_CHANCE = 0.2;
    private static final int MAX_AGE = Integer.MAX_VALUE ;
    private TreeFactory factory;

    /**
     * Construct a mature tree.
     */
    public MatureTree(TreeFactory factory) {
        super('T', factory,MAX_AGE);
        this.factory = factory;
    }


    /**
     * Processes a single tick for the tree, potentially spawning fruit based on a predefined spawn chance.
     * The tree's age is incremented each tick, and if the random chance conditions are met, the tree will
     * spawn fruit at its location.
     * @param location the location of the tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (Math.random()<= SPAWN_CHANCE && this.getAge() < this.getMaxAge()) {
            Fruit fruit = new LargeFruit();
            spawnFruit(location,fruit);
        }
    }

    /**
     * Mature tree is not growing any further, it will only keep growing as a mature tree
     * @return a mature tree
     */
    @Override
    public Inheritree createNextStage() {
        return factory.createMatureTree();
    }
}
