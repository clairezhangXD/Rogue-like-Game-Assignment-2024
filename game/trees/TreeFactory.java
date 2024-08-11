package game.trees;


/**
 * A factory interface for creating various stages of trees. Implementations of this interface
 * provide methods to create specific tree stages, facilitating the transition between different
 * stages in a tree's lifecycle.
 *
 * @author Chloe Ang
 */
public interface TreeFactory {

    /**
     * Creates an instance of a Sprout tree stage.
     *
     * @return a new instance of a Sprout
     */
    public Inheritree createSprout();

    /**
     * Creates an instance of a Sapling tree stage.
     *
     * @return a new instance of a Sapling Tree
     */
    public Inheritree createSaplingTree();

    /**
     * Creates an instance of a Young tree stage.
     *
     * @return a new instance of a Young Tree
     */
    public Inheritree createYoungTree();

    /**
     * Creates an instance of a Mature tree stage.
     *
     * @return a new instance of a Mature Tree
     */
    public Inheritree createMatureTree();
}
