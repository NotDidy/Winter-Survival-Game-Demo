package game.animals;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import game.actions.AttackAction;
import game.actions.TameAction;
import game.animals.behaviors.FollowBehaviour;
import game.animals.behaviors.HealingBehaviour;
import game.animals.behaviors.WanderBehaviour;
import game.interfaces.Tameable;
import game.items.TamingItem;
import game.items.TamingItemType;
import game.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deer extends Actor implements Tameable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private boolean tamed = false;
    private Actor owner;

    public Deer() {
        super("Deer", 'd', 50); // name, displayChar, hitPoints
        behaviours.put(10, new WanderBehaviour()); // wander around
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Pick the highest priority behaviour available
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.generateAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (!tamed) {
            actions.add(new AttackAction(this, direction));
        }


        List<Item> playerItems = otherActor.getItemInventory();
        for (Item item : playerItems) {
            // use asCapability to safely check if item has TamingItem capability
            item.asCapability(TamingItem.class).ifPresent(tamingItem -> {
                // check if this Wolf can be tamed with that TamingItem
                if (this.canBeTamedWith(tamingItem)) {
                    actions.add(new TameAction(this, tamingItem));
                }
            });
        }


        return actions;
    }

    @Override
    public TamingItemType getTamingItemType() {
        return TamingItemType.FOR_NON_PREDATORS;
    }


    @Override
    public void onTamedBy(Player player) {
        this.tamed = true;
        this.owner = player;
        behaviours.clear(); // stop random wandering
        behaviours.put(10, new FollowBehaviour(player));
        behaviours.put(5, new HealingBehaviour(player, 20, 70));

    }

    @Override
    public boolean isTamed() {
        return tamed;
    }
}