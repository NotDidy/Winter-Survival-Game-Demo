package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Drinkable;

public class DrinkAction extends Action {
    private final Drinkable item;

    public DrinkAction (Drinkable item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.drunkBy(actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from " + item;
    }


}
