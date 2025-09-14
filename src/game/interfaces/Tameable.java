package game.interfaces;

import game.items.TamingItemType;
import game.items.TamingItem;
import game.player.Player;

public interface Tameable {



    default boolean canBeTamedWith(TamingItem item) {
        return item.getConsumableType() == getTamingItemType();
    }
    TamingItemType getTamingItemType();


    void onTamedBy(Player player);
    boolean isTamed();
}

