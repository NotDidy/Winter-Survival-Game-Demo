package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.items.iteminterfaces.Consumable;
import game.player.Player;

/**
 * Apple item that can be consumed by the Explorer.
 * Restores 3 HP and reduces thirst by 2.
 */
public class Apple extends Item implements Consumable {

    public Apple() {
        super("Apple", 'a', true);
        // Add a consume action so Explorer can eat it

    }

    @Override
    public String consumedBy(Player player) {
        player.heal(3); // restore health
        player.increaseHydration(2); // custom method in Player to lower thirst
        return player + " consumes " + this.getClass().getSimpleName() + " and increases hydration by 2 and heals by 3";
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }


    /**
     * Called when the item is consumed.
     */


}