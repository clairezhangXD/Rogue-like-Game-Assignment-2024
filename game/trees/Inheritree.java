package game.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An abstract class that represents a type of Ground, this class serves as a blueprint of the tree classes and provides
 * the common behaviors of all tree stages, it can be inherited by other concrete tree classes to implement specific
 * tree stage logic.
 * @see Ground
 * Created by:
 * @author Jin Ruo Yew
 * Modified by:
 * @author Chloe Ang
 */
public abstract class Inheritree extends Ground {


    private TreeFactory factory;
    private int maxAge;
    private int age = 0;


    /**
     * Constructor.
     * @param displayChar the character that will represent the Inheritree on the map
     * @param factory the specific type of Inheritree the tree will grow into upon reaching its max age or null if it does not transition to another stage.
     * @param maxAge the maximum age the tree can reach before it attempts to transition to its next stage.
     *               The tree grow into next stage when its age reaches this value.
     */
    public Inheritree(char displayChar, TreeFactory factory, int maxAge){
        super(displayChar);
        this.factory = factory;
        this.maxAge = maxAge;

    }

    /**
     * Returns the current age of the tree.
     * @return the age of the tree
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Return the max age of the tree before growing into next stage.
     * @return the maximum age after which the tree will grow into its next stage.
     */
    public int getMaxAge() {
        return this.maxAge;
    }

    /**
     * Abstract method to create the next stage of the tree. Concrete classes must implement this method
     * to specify the next stage in the tree's lifecycle.
     * @return the next stage of the tree
     */
    public abstract Inheritree createNextStage();

    /**
     * Processes a single tick for the tree by incrementing its age. If the tree reaches its maximum age of a specific stage
     * and a next stage is defined, it transitions to the next stage by updating its location's ground.
     *
     * @param location the current location of the tree which might be updated if the tree grow into a new stage.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.age++;

        // optional, it will grow into next stage after reaching the max age
        if (this.age >= this.maxAge) {
            Inheritree nextStage = createNextStage();
            location.setGround(nextStage);
        }
    }
}
