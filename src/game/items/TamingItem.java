package game.items;

import edu.monash.fit2099.engine.items.Item;


public abstract class TamingItem extends Item {
    private final TamingItemType type;

    public TamingItem(String name, char displayChar, boolean portable, TamingItemType type) {
        super(name, displayChar, portable);
        this.type = type;
    }

    public TamingItemType getConsumableType() {
        return type;
    }


}