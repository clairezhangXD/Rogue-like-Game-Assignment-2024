package game.trees;

/**
 * A class that represents a type of Inheritree, Young Tree.
 * This tree can't spawn fruit but will grow into a Young Tree after reaching its maximum age of 5 ticks.
 * @see Inheritree
 * @see MatureTree
 * Created by:
 * @author Chloe Ang
 */

public class YoungTree extends Inheritree {

    private TreeFactory factory;
    private static final int MAX_AGE = 5;

    /**
     * Construct a Young Tree, this grows into a Mature Tree once it reaches its maximum age.
     */
    public YoungTree(TreeFactory factory) {
        super('y', factory,MAX_AGE);
        this.factory = factory;
    }

    /**
     * Creates the next stage of the tree, which is a Mature Tree.
     *
     * @return the next stage of the tree
     */
    @Override
    public Inheritree createNextStage() {
        return factory.createMatureTree();
    }
}
