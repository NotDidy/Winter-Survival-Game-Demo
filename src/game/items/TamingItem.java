package game.items;

import edu.monash.fit2099.engine.items.Item;


public class TamingItem extends Item {
    private final ConsumableType type;

    public TamingItem(String name, char displayChar, boolean portable, ConsumableType type) {
        super(name, displayChar, portable);
        this.type = type;
    }

    public ConsumableType getConsumableType() {
        return type;
    }


}