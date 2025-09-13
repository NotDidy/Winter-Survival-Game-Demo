package game.world;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.items.Bedroll;
import game.player.Player;

import java.util.Arrays;
import java.util.List;

public class Earth extends World {
    Player player;
    public Earth(Display display) {
        super(display);
        this.player = new Player("Explorer", 'ඞ', 100, 20, 30);
    }

    public void constructWorld() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Snow::new);

        List<String> map = Arrays.asList(
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................",
                "........................................"
        );

        GameMap gameMap = new GameMap("Forest", groundCreator, map);
        this.addGameMap(gameMap);


        this.addPlayer(this.player, gameMap.at(1, 1));
        this.player.addItemToInventory(new Bedroll());
    }


    @Override
    protected boolean stillRunning() {

        if (!player.isConscious()) {
            return false;

        }
        else {
            return this.actorLocations.contains(this.player);
        }
    }
}
