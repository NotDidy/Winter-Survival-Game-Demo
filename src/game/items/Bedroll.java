package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SleepAction;
import game.interfaces.Sleepable;
import game.player.Player;

import java.util.Random;

public class Bedroll extends Item implements Sleepable {

    private final Random rand = new Random();
    /***
     * Constructor.
     */
    public Bedroll() {
        super("Bedroll", '=', true);
    }

    //Implement Logic
    @Override
    public String sleptBy(Player player) {
        int turns = 6 + rand.nextInt(5);
        player.setSleepTurns(turns);
        return player + " lies down and sleeps for " + turns + " turns.";
    }

    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();

        // Only usable if it’s on the ground
        if (location.containsAnActor()) {
            actions.add(new SleepAction(this));
        }

        return actions;
    }


}
