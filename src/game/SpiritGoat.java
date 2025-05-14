package game;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpiritGoat class represents a unique non-player character (NPC) in the game.game.
 * It extends the Actor class and contains behaviors specific to the Spirit Goat.
 * The Spirit Goat can roam the map autonomously and be interacted with by other actors.
 * It has a defined set of actions it can execute during its turn in the game.game.
 */
public class SpiritGoat extends Actor {
    private final List<Behaviour> behaviours = new ArrayList<>();

    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        behaviours.add(new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour b : behaviours) {
            Action a = b.getAction(this, map);
            if (a != null) {
                return a;
            }
        }
        return new DoNothingAction();
    }
    /**
     * The spirit goat can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param other the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that can be performed on the spirit goat
     */
    @Override
    public ActionList allowableActions(Actor other, String direction, GameMap map) {

        ActionList list = new ActionList();
        if (other.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            list.add(new AttackAction(this, direction));
        }
        return list;
    }
}
