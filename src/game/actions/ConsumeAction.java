package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Consumable;
import game.player.Player;

public class ConsumeAction extends Action {
    private final Consumable item;

    public ConsumeAction(Consumable item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.consumedBy(actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + item;
    }

}
