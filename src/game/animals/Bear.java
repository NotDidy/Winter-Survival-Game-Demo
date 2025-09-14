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
import game.animals.behaviors.*;

import game.interfaces.Tameable;
import game.items.TamingItem;
import game.items.TamingItemType;
import game.player.Player;
import game.weapons.BearClaw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Wolf that roams the world and can bite other actors.
 */
public class Bear extends Actor implements Tameable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private boolean tamed = false;
    private Actor owner;

    public Bear() {
        super("Bear", 'B', 200);
        this.setIntrinsicWeapon(new BearClaw());
        behaviours.put(5, new AttackBehaviour());
        behaviours.put(10, new WanderBehaviour());
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


        if (!tamed) {
            actions.add(new AttackAction(this, direction));
        }

        List<Item> playerItems = otherActor.getItemInventory();
        for (Item item : playerItems) {

            item.asCapability(TamingItem.class).ifPresent(tamingItem -> {

                if (this.canBeTamedWith(tamingItem)) {
                    actions.add(new TameAction(this, tamingItem));
                }
            });
        }



        return actions;
    }

    @Override
    public TamingItemType getTamingItemType() {
        return TamingItemType.FOR_PREDATORS;
    }


    @Override
    public void onTamedBy(Player player) {
        this.tamed = true;
        this.owner = player;
        behaviours.clear();
        behaviours.put(10, new FollowBehaviour(player));
        behaviours.put(5, new ProtectOwnerBehaviour(player));

    }

    @Override
    public boolean isTamed() {
        return tamed;
    }


}
