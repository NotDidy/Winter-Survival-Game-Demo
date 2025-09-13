package game.animals;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import game.animals.behaviors.WanderBehaviour;
//import edu.monash.fit2099.engine.behaviours.Behaviour;
//import edu.monash.fit2099.engine.behaviours.WanderBehaviour;

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
}