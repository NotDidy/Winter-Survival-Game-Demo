package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.items.itemactions.SleepAction;

public class Bedroll extends Item {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bedroll(String name, char displayChar, boolean portable) {
        super("Bedroll", '=', true);
    }

    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();

        // Only usable if it’s on the ground
        if (!location.containsAnActor()) {
            actions.add(new SleepAction());
        }

        return actions;
    }
}
