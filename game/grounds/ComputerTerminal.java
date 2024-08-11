package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;

import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PurchaseAction;

import game.actions.TravelAction;
import game.scraps.DragonSlayerSword;
import game.scraps.EnergyDrink;
import game.scraps.Theseus;
import game.scraps.ToiletPaperRoll;
import game.scraps.Astley;

import java.util.ArrayList;

/**
 *  A class that represents a type of Ground, a Computer terminal that the player
 *  can buy items from or travel to other maps.
 */
public class ComputerTerminal extends Ground {

    private ArrayList<TravelAction> travelActions = new ArrayList<>();

    /**
     * constructor
     */
    public ComputerTerminal() {
        super('=');
    }

    /**
     * Actions that the Actor can select on the Computer terminal when they are next to or standing on it
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions that the Actor can select on the Computer terminal when they are next to or standing on it
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        actions.add(new PurchaseAction(new EnergyDrink()));
        actions.add(new PurchaseAction(new DragonSlayerSword()));
        actions.add(new PurchaseAction(new ToiletPaperRoll()));
        actions.add(new PurchaseAction(new Astley()));
        actions.add(new PurchaseAction(new Theseus()));
        for (Action action : travelActions) {
            actions.add(action);
        }

        return actions;
    }

    /**
     * add a travel action to the list of possible travel actions available to the actor from this computer terminal
     * @param travelAction the new travel action available to the actor from this computer terminal
     */
    public void addTravelAction(TravelAction travelAction){
        this.travelActions.add(travelAction);
    }


}
