package game.animals;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import game.actions.AttackAction;
import game.animals.behaviors.AttackBehaviour;
import game.animals.behaviors.WanderBehaviour;

import game.weapons.BearClaw;

import java.util.HashMap;
import java.util.Map;

/**
 * A Wolf that roams the world and can bite other actors.
 */
public class Bear extends Actor {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    public Bear() {
        super("Bear", 'B', 200); // name, display char, hitpoints
        this.setIntrinsicWeapon(new BearClaw());
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

        return actions;
    }


}
