package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.player.Player;

/**
 * Snake-like follow behaviour: animal moves into the player's previous position.
 * All logic is contained inside this Behaviour.
 */
public class FollowBehaviour implements Behaviour {

    private final Player owner;
    private Location lastOwnerLocation; // remembers where owner was last turn

    public FollowBehaviour(Player owner) {
        this.owner = owner;
    }

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        if (!map.contains(owner)) {
            return new DoNothingAction();
        }

        Location ownerLocation = map.locationOf(owner);


        if (lastOwnerLocation != null) {
            // If follower is already standing there, no need to move
            if (!map.locationOf(actor).equals(lastOwnerLocation)) {
                if (!map.isAnActorAt(lastOwnerLocation) && lastOwnerLocation.canActorEnter(actor)) {
                    // Move into owner's last position
                    Action action = new MoveActorAction(lastOwnerLocation, "follows " + owner);
                    // Update for next turn
                    lastOwnerLocation = ownerLocation;
                    return action;
                }
            }
        }

        // Update owner's last location for the next turn
        lastOwnerLocation = ownerLocation;

        return new DoNothingAction();
    }
}
