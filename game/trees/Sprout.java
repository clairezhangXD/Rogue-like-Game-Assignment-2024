package game.trees;


/**
 * A class that represents a type of Inheritree, Sprout.
 * This tree can't spawn fruit but will grow into a Sapling Tree after reaching its maximum age of 3 ticks.
 * @see Inheritree
 * @see SaplingTree
 * Created by:
 * @author Chloe Ang
 *
 */
public class Sprout extends Inheritree {

    private TreeFactory factory;
    private static final int MAX_AGE = 3 ;

    /**
     * Construct a Sprout, this tree grows into a Sapling Tree once it reaches its maximum age.
     */
    public Sprout(TreeFactory factory) {
        super(',',factory,MAX_AGE);
        this.factory = factory;
    }

    /**
     * Creates the next stage of the tree, which is a Sapling Tree.
     *
     * @return the next stage of the tree
     */
    @Override
    public Inheritree createNextStage() {
        return factory.createSaplingTree();
    }
}
