package game.animals;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import game.actions.AttackAction;
import game.actions.TameAction;
import game.animals.behaviors.AttackBehaviour;
import game.animals.behaviors.FollowBehaviour;
import game.animals.behaviors.WanderBehaviour;

import game.interfaces.Tameable;
import game.items.ConsumableType;
import game.items.TamingItem;
import game.player.Player;
import game.weapons.WolfBite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Wolf that roams the world and can bite other actors.
 */
public class Wolf extends Actor implements Tameable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private boolean tamed = false;
    private Actor owner;

    public Wolf() {
        super("Wolf", 'e', 100); // name, display char, hitpoints
        this.setIntrinsicWeapon(new WolfBite());
        behaviours.put(5, new AttackBehaviour()); // higher priority → attack first
        behaviours.put(10, new WanderBehaviour()); // lower priority → wander if no attack
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.generateAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return lastAction.getNextAction(); // do nothing if no behaviour applies
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();


        actions.add(new AttackAction(this, direction));

        List<Item> playerItems = otherActor.getItemInventory();
        for (Item item : playerItems) {
            if (canBeTamedWith((TamingItem) item)) {
                actions.add(new TameAction(this, (TamingItem) item));
            }
        }



        return actions;
    }

    @Override
    public ConsumableType getPreferredFoodType() {
        return ConsumableType.MEAT;
    }


    @Override
    public void onTamedBy(Player player) {
        this.tamed = true;
        this.owner = player;
        behaviours.remove(5); // stop random wandering
        behaviours.put(10, new FollowBehaviour(player));
        behaviours.put(5, new ProtectOwnerBehaviour(player));

    }

    @Override
    public boolean isTamed() {
        return tamed;
    }


}
