package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * An action where one Actor attacks another using bare fists by default.
 */
public class AttackAction extends Action {
    private final Actor target;
    private final String direction;


    // Bare fist attack values (can be adjusted or replaced by weapons later)
    private static final int BARE_FIST_DAMAGE = 5;
    private static final int BARE_FIST_HIT_CHANCE = 50; // percentage

    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //Player attacking target
        System.out.println(actor.getIntrinsicWeapon().attack(actor,target,map));


        // Check if target is dead
        if (!target.isConscious()) {
            return target.unconscious(actor, map);

            //map.removeActor(target);
            //return actor + " punches " + target + " for " + BARE_FIST_DAMAGE + " damage and kills it!";
        } else {
            return " ";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " punches " + target;
    }
}
