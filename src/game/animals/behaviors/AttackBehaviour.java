package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * Behaviour that lets an Actor attack other nearby actors.
 */
public class AttackBehaviour implements Behaviour {

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor target = map.getActorAt(destination);
                if (target != null && target != actor) {
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null; // no attack possible
    }
}