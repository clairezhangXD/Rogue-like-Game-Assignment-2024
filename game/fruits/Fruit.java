package game.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * An abstract class representing a fruit item This class extends the Item class and provides a blueprint for fruit items.
 */
public abstract class Fruit extends Item{

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Fruit(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Get the list of allowable actions for the actor to perform on the fruit item.
     * @param owner the actor that is interacting with the fruit item
     * @return the list of allowable actions for the actor to perform with the fruit item
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        return actions;
    }
}