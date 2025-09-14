package game.animals.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.player.Player;

/**
 * Behaviour that allows a tamed animal to heal its owner or itself if health is low.
 * Only triggers when healing is actually needed.
 */
public class HealingBehaviour implements Behaviour {

    private final Player owner;
    private final int healAmount;
    private final int healthThreshold;

    /**
     * @param owner The player to heal
     * @param healAmount Amount of health to restore
     * @param healthThreshold Minimum health to trigger healing
     */
    public HealingBehaviour(Player owner, int healAmount, int healthThreshold) {
        this.owner = owner;
        this.healAmount = healAmount;
        this.healthThreshold = healthThreshold;
    }

    @Override
    public Action generateAction(Actor actor, GameMap map) {
        // Heal the owner if their health is below threshold
        if (owner.getAttribute(BaseAttributes.HEALTH) < healthThreshold) {
            return new Action() {
                @Override
                public String execute(Actor actor, GameMap map) {
                    int healed = Math.min(
                            healAmount,
                            owner.getMaximumAttribute(BaseAttributes.HEALTH) - owner.getAttribute(BaseAttributes.HEALTH)
                    );
                    owner.heal(healed);
                    return actor + " heals " + owner + " for " + healed + " hitpoints.";
                }

                @Override
                public String menuDescription(Actor actor) {
                    return actor + " heals " + owner;
                }
            };
        }



        // Nothing to heal -> let other behaviours run
        return null;
    }
}
