package game.interfaces;

import game.items.ConsumableType;
import game.items.TamingItem;
import game.player.Player;

public interface Tameable {



     default boolean canBeTamedWith(TamingItem item) {
        return item.getConsumableType() == getPreferredFoodType();
    }
    ConsumableType getPreferredFoodType();


    void onTamedBy(Player player);
    boolean isTamed();
}

