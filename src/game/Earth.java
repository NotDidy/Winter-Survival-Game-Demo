package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

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
    }

    @Override
    protected boolean stillRunning() {

        if (this.player.getHydration() < 1 || this.player.getWarmth() < 1) {
            System.out.println(this.player + " perished");
            return false;
        }
        else {
            return this.actorLocations.contains(this.player);
        }
    }
}
