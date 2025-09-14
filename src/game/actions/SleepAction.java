package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Sleepable;





public class SleepAction extends Action {
    private final Sleepable item;



    public SleepAction(Sleepable item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.sleptBy(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sleeps on the bedroll";
    }
}