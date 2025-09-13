package game.animals;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import game.actions.AttackAction;
import game.animals.behaviors.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

public class Deer extends Actor {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

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

        // The Deer cannot attack back (for now)
        // But Explorer (or other actors) can attack it
        actions.add(new AttackAction(this, direction));

        return actions;
    }
}