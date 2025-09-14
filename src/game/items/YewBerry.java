package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.interfaces.Consumable;
import game.player.Player;

/**
 * Apple item that can be consumed by the Explorer.
 * Restores 3 HP and reduces thirst by 2.
 */
public class YewBerry extends Item implements Consumable {

    public YewBerry() {
        super("Yew Berry", 'x', true);
        // Add a consume action so Explorer can eat it

    }

    @Override
    public String consumedBy(Actor actor) {
        Player player = (Player) actor;
        player.modifyAttribute(BaseAttributes.HEALTH, ActorAttributeOperation.UPDATE, 0);
        player.removeItemFromInventory(this);// custom method in Player to lower thirst
        return player + " consumed a " + this.getClass().getSimpleName() + " and instantly died.";
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }





}