package game.trees;

/**
 * A concrete factory class for creating various stages of trees. This class implements the TreeFactory
 * interface and provides specific logic of implementations to create different tree stages in a tree's lifecycle.
 */
public class TreeStageFactory implements TreeFactory {

    /**
     * Creates an instance of a Sprout
     *
     * @return a new instance of a Sprout
     */
    public Inheritree createSprout() {
        return new Sprout(this);
    }

    /**
     * Creates an instance of a Sapling
     *
     * @return a new instance of a Sapling
     */
    public Inheritree createSaplingTree() {
        return new SaplingTree(this);
    }

    /**
     * Creates an instance of a Young Tree
     *
     * @return a new instance of a Young Tree
     */
    public Inheritree createYoungTree() {
        return new YoungTree(this);
    }

    /**
     * Creates an instance of a Mature Tree
     *
     * @return a new instance of a Mature Tree
     */
    public Inheritree createMatureTree() {
        return new MatureTree(this);
    }
}
