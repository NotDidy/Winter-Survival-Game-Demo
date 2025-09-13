package game.items;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.itemactions.DrinkAction;
import game.items.iteminterfaces.Drinkable;
import game.player.Player;

public class Bottle extends Item implements Drinkable {
    private int capacity = 5;
    public Bottle() {
        super("Bottle", 'o', true);

    }



    @Override
    public String drunkBy(Player player) {
        if (capacity > 0) {
            capacity--;
            player.increaseHydration(4);
            return player + " drank from " + this.getClass().getSimpleName() + ". The remaining amount of uses left is " + capacity;
        }else {
            return player + " tried to drink from " + this.getClass().getSimpleName() + " but there was no water left"; // no hydration if empty
        }

    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new DrinkAction(this));
        return actions;
    }



}
