package game.interfaces;

import edu.monash.fit2099.engine.items.Item;
import game.player.Player;

public interface Tameable {
    boolean canBeTamedWith(Item item);
    void onTamedBy(Player player);
}

