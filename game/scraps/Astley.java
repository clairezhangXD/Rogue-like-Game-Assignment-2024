package game.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MonologueAction;
import game.scraps.Purchasable;

import java.util.ArrayList;
import java.util.Arrays;

public class Astley extends Item implements Purchasable {
    /**
     * Cost of the Astley AI.
     */
    private static final int COST = 50;

    /**
     * Amount of time (ticks) before charging actor for its subscription.
     */
    private static final int SUBSCRIPTION_TIME = 5;

    /**
     * Amount charged for the subscription of this device.
     */
    private static final int SUBSCRIPTION_PRICE = 1;

    /**
     * Minimum inventory size for chance of specific line to be played
     */
    private static final int MIN_INVENTORY_SIZE = 10;

    /**
     * Minimum player balance for chance of specific line to be played
     */
    private static final int MIN_BALANCE = 50;

    /**
     * Max health of player for chance of specific line to be played
     */
    private static final int MAX_HEALTH = 2;

    /**
     * Counter for amount of time (ticks) that has passed.
     */
    private int tickCounter;

    /**
     * Boolean representing whether player has paid subscription fee or not
     * to decider whether the player can interact with the AI or not
     */
    private boolean paid = true;

    /**
     * Name of AI device.
     */
    private static final String NAME = "Astley, an AI device";

    /**
     * Arraylist of strings that represent the list of what the AI can say
     */
    private ArrayList<String> monologues = new ArrayList<>
            (Arrays.asList("The factory will never gonna give you up, valuable intern!",
                    "We promise we never gonna let you down with a range of staff benefits.",
                    "We never gonna run around and desert you, dear intern!"));

    /***
     * Constructor.
     */
    public Astley() {
        super(NAME, 'z', true);
        this.tickCounter = 0;
    }

    /**
     * Get the cost of the AI.
     * @return an integer representing the cost of the AI.
     */
    public int getCost() {
        return COST;
    }

    /**
     * Get the AI.
     * @return the AI.
     */
    public Item getItem() {
        return this;
    }

    /**
     * Execute the action of purchasing the Astley by the actor.
     * @param actor the Actor that is purchasing the Astley.
     * @return a string representing the result of the purchase of the Astley.
     */
    public String executePurchase(Actor actor) {
        String ret = "";
        actor.addItemToInventory(this);
        actor.deductBalance(COST);
        ret += "\n" + actor + " purchased " + this + " for $" + COST;

        return ret;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        tickCounter++;
        if (tickCounter >= SUBSCRIPTION_TIME){
            tickCounter = 0;
            subscribe(actor);
        }
    }

    /**
     * Checks whether player can pay for subscription of the AI, if
     * they can, the payment is deducted from their balance, if not
     * paid boolean turns to false and player can no longer interact
     * with the AI
     * @param actor The actor carrying the AI.
     */
    public void subscribe(Actor actor){
        double balance = actor.getBalance();
        if (balance >= SUBSCRIPTION_PRICE){
            System.out.println("Subscription payment received!");
            actor.deductBalance(SUBSCRIPTION_PRICE);
            paid = true;
        }
        else{
            paid = false;
        }
    }

    /**
     * List of allowable actions that the actor which has the Astley AI can perform
     * @param owner the actor that owns the item
     * @return a list of actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
        if (paid){
            int removeCounter = 0;
            if (owner.getItemInventory().size() > MIN_INVENTORY_SIZE){
                monologues.add("We never gonna make you cry with unfair compensation.");
                removeCounter++;
            }
            if(owner.getBalance() > MIN_BALANCE){
                monologues.add("Trust is essential in this business. We promise we never gonna " +
                        "say goodbye to a valuable intern like you.");
                removeCounter++;
            }
            if(owner.getAttribute(BaseActorAttributes.HEALTH) < MAX_HEALTH){
                monologues.add("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.");
                removeCounter++;
            }
            actions.add(new MonologueAction(new ArrayList<>(monologues),NAME));

            for (int i = 0; i < removeCounter; i++){
                monologues.remove(monologues.size()-1);
            }
        }
        return actions;
    }
}
