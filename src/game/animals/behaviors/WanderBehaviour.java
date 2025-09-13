package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Behaviour that causes the Actor to wander around randomly.
 */
public class WanderBehaviour implements Behaviour {
    private final Random random = new Random();

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        List<Exit> exits = here.getExits();
        List<Exit> possibleMoves = new ArrayList<>();

        // Collect all exits the actor can move into
        for (Exit exit : exits) {
            if (exit.getDestination().canActorEnter(actor)) {
                possibleMoves.add(exit);
            }
        }

        if (!possibleMoves.isEmpty()) {
            Exit chosenExit = possibleMoves.get(random.nextInt(possibleMoves.size()));


            return new MoveActorAction(chosenExit.getDestination(), chosenExit.getName());
        }

        return null; // no moves available
    }
}