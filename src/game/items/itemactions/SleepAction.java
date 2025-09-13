package game.items.itemactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.player.Player;

import java.util.Random;

public class SleepAction extends Action {
    private final Random rand = new Random();

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player player) {
            // Choose random turns 6–10
            int turns = 6 + rand.nextInt(5);

            player.setSleeping(turns); // Player needs a flag to skip hydration/warmth loss

            return actor + " lies down and sleeps for " + turns + " turns.";
        }
        return actor + " cannot use the bedroll.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sleeps on the bedroll";
    }
}