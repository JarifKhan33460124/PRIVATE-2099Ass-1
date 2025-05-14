package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;

/**
 * Class representing the Player.

 *  * The Player class represents the Farmer in “ELDEN THING: THE VALLEY OF THE INHERITREE.”
 *  * It extends Actor and adds stamina, displays HP & stamina each turn,
 *  * and configures the Farmer to be hostile and use bare fists.
 * @author Adrian Kristanto
 * * Modified by: Jarif khan
 */
public class Player extends Actor {
    /** The Farmer’s stamina pool. */
    private int stamina;
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        // Add stamina to the player
        this.stamina = 200;
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new BareFist());
    }
    /**
     * @return current stamina
     */
    public int getStamina() {
        return stamina;
    }
    /**
     * @param stamina new stamina value
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }


    /**
     * Each turn, display HP & stamina, then either continue a multi‐turn action
     * or show the action menu.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Always show the Farmer’s stats

        // Continue any multi‐turn action in progress
        if (lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }

        // Otherwise present the normal menu of actions
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    @Override
    public String toString() {
        // super.toString() returns "Farmer (100/100)"
        return super.toString() + " | Stamina: " + stamina;
    }



}
