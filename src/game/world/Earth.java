package game.world;

import edu.monash.fit2099.demo.forest.HuntsmanSpider;
import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.animals.Bear;
import game.animals.Deer;
import game.animals.Wolf;
import game.items.*;
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
        groundCreator.registerGround('A', HazelnutTree ::new);

        List<String> map = Arrays.asList(
                "........................................",
                ".............A..........................",
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
        //gameMap.at(2, 1).addActor(new Bear());
        //gameMap.at(7, 6).addActor(new Wolf());
        //gameMap.at(3, 3).addActor(new Deer());
        this.player.addItemToInventory(new Bedroll());
        this.player.addItemToInventory(new Bottle());
        this.player.addItemToInventory(new YewBerry());
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
