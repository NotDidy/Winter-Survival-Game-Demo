package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

import java.util.Random;

/**
 * A Behaviour that causes the Actor to wander around randomly.
 */
public class WanderBehaviour implements Behaviour {
    private final Random random = new Random();

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for(Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(this.random.nextInt(actions.size()));
        } else {
            return null;
        }
    }
}