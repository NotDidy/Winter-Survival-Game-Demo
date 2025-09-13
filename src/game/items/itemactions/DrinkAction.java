package game.items.itemactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.iteminterfaces.Drinkable;
import game.player.Player;

public class DrinkAction extends Action {
    private final Drinkable item;

    public DrinkAction (Drinkable item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.drunkBy((Player) actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from " + item;
    }


}
