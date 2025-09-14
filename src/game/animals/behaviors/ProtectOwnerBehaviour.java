package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.AttackAction;
import game.player.Player;

/**
 * Behaviour for a tamed predator to protect its owner by attacking nearby hostiles.
 */
public class ProtectOwnerBehaviour implements Behaviour {

    private final Player owner;

    public ProtectOwnerBehaviour(Player owner) {
        this.owner = owner;
    }

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        if (!map.contains(owner)) {
            return null;
        }

        Location ownerLocation = map.locationOf(owner);

        // Check all exits around the owner
        for (Exit exit : ownerLocation.getExits()) {
            Location dest = exit.getDestination();
            if (map.isAnActorAt(dest)) {
                Actor potentialEnemy = map.getActorAt(dest);

                // Prevent attacking the owner or itself
                if (potentialEnemy != owner && potentialEnemy != actor) {
                    // Later you could refine: check if enemy is hostile type
                    return new AttackAction(potentialEnemy, "to protect its owner");
                }
            }
        }
        return null;
    }
}
