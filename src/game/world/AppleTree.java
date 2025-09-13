package game.world;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Apple;

import java.util.List;
import java.util.Random;

/**
 * A wild apple tree that drops apples every 3 turns.
 */
public class AppleTree extends Ground {

    private int turnCounter = 0;

    private final Random random = new Random();

    public AppleTree() {
        super('T', "Apple Tree"); // Tree display char
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turnCounter++;

        // Drop apples exactly every 3 turns
        if (turnCounter == 3) {
            turnCounter = 0;

            List<Exit> exits = location.getExits();
            if (!exits.isEmpty()) {
                // Pick a random surrounding location
                Exit randomExit = exits.get(random.nextInt(exits.size()));
                Location dropLocation = randomExit.getDestination();

                dropLocation.addItem(new Apple());
                System.out.println("A wild apple drops near a tree.");
            }
        }
    }
}