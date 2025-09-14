package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Tameable;
import game.items.TamingItem;
import game.player.Player;

public class TameAction extends Action {
    private final Tameable target;
    private final TamingItem item;

    public TameAction(Tameable target, TamingItem item) {
        this.target = target;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        target.onTamedBy((Player) actor); //move this to other classes
        actor.removeItemFromInventory(item); // consume food
        return actor + " tames " + target + " with " + item;

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Tame " + target + " with " + item;
    }
}