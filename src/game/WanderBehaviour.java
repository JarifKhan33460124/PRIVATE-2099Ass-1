
package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Jarif Khan

 * A reusable wander behaviour: picks a random adjacent exit
 * and returns a MoveActorAction, or null if none valid.
 */

public class WanderBehaviour implements Behaviour {
    private final Random rand = new Random();
    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        List<Exit> exits = new ArrayList<>(here.getExits());
        Collections.shuffle(exits, rand);

        for (Exit exit : exits) {
            Location dest = exit.getDestination();
            // only move if the destination is free and enterable
            if (dest.getActor() == null && dest.canActorEnter(actor)) {
                return new MoveActorAction(dest, exit.getName());
            }
        }
        return null;
    }
}
