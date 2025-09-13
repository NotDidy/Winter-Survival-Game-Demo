package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 * @author Adrian Kristanto
 */
public class BearClaw extends IntrinsicWeapon {
    public BearClaw() {
        super(75, "claws", 80, "bear claw");
    }
}