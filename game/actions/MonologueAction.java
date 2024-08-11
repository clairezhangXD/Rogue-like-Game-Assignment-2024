package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utilities.Utility;

import java.util.ArrayList;

public class MonologueAction extends Action {

    /**
     * Arraylist of strings to represent the possible monologues that could be displayed
     */
    private final ArrayList<String> texts;

    /**
     * Speaker of the monologue
     */
    private final String speaker;

    /**
     *
     * @param texts Arraylist of strings to represent the possible monologues that could be displayed
     * @param name The name of the speaker of the monologue
     */
    public MonologueAction(ArrayList<String> texts, String name){
        this.texts = texts;
        this.speaker = name;
    }

    /**
     * Executes the monologue action by randomly selecting one of the
     * strings in the arraylist provided in the constructor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the speaker saying the monologue
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return speaker + ": \"" + texts.get(Utility.generateRandomInt(0, texts.size()-1)) + "\"";
    }

    /**
     * Describe what action will be performed if MonologueAction is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + speaker;
    }
}
