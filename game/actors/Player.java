package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Ability;
import game.FancyMessage;
import game.Status;
import game.actions.AttackAction;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Jin Ruo Yew
 *
 */
public class Player extends Actor {

    /**
     * Constant for the player's intrinsic weapon - bare hands.
     */
    private static final int PLAYER_INTRINSIC_WEAPON_DAMAGE = 1;

    /**
     * Constant for the player's hit rate.
     */
    private static final int PLAYER_HIT_RATE = 5;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.SELL_ITEM);
    }

    /**
     * At each turn, select a valid action for the Player to perform.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid actions that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Display current status
        display.println("HP: " + this.toString());
        display.println("Balance: " + this.getBalance());

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Get the intrinsic weapon of the player.
     * @return the intrinsic weapon of the player
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = Math.round(PLAYER_INTRINSIC_WEAPON_DAMAGE * damageMultiplier);
        return new IntrinsicWeapon(damage, "punches", PLAYER_HIT_RATE);
    }

    /**
     * Method that can be executed when the Player is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     * @see Actor
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        return FancyMessage.YOU_ARE_FIRED + "\n" + super.unconscious(actor, map);
    }

}
