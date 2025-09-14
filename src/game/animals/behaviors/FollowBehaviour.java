package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.player.Player;

/**
 * Behaviour for a tamed animal to follow its owner.
 */
public class FollowBehaviour implements Behaviour {

    private final Player owner;

    public FollowBehaviour(Player owner) {
        this.owner = owner;
    }

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        // if owner not on map, nothing to do
        if (!map.contains(owner)) {
            return null;
        }

        Location ownerLocation = map.locationOf(owner);

        // already at owner's location
        if (here.equals(ownerLocation)) {
            return null;
        }

        // check exits, move closer if possible
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().equals(ownerLocation)) {
                return new MoveActorAction(exit.getDestination(), exit.getName());
            }
        }

        // simple version: just pick first exit closer to owner
        for (Exit exit : here.getExits()) {
            Location dest = exit.getDestination();
            int currentDistance = distance(here, ownerLocation);
            int newDistance = distance(dest, ownerLocation);

            if (newDistance < currentDistance) {
                return new MoveActorAction(dest, exit.getName());
            }
        }
        return null;
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
