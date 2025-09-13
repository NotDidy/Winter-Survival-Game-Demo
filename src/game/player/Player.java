package game.player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.weapons.BareFist;

/**
 * Class representing the Player.
 * @author Adrian Kristanto
 */
public class Player extends Actor {
    private int hydration;

    private int warmth;

    private int sleepTurns = 0;

    private static final int HYDRATION_DECREASE_VALUE = 1;
    private static final int WARMTH_DECREASE_VALUE = 1;
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int hydration, int warmth) {
        super(name, displayChar, hitPoints);
        this.setIntrinsicWeapon(new BareFist());
        this.hydration = hydration;
        this.warmth = warmth;


    }

    public void decreaseHydration (int value){
        hydration = hydration - value;
    }

    public void decreaseWarmth (int value){
        warmth = warmth - value;
    }

    public int getHydration(){
        return this.hydration;
    }

    public int getWarmth(){
        return this.warmth;
    }

    public void setSleepTurns(int turns) {
        this.sleepTurns = turns;
    }

    public void increaseHydration(int value) {
        hydration = hydration + value;
    }


    @Override
    public boolean isConscious() {
        if (this.getHydration() < 1 || this.getWarmth() < 1) {
            System.out.println(this.name + " perished");
            return false;
        }
        else {
            return this.getAttribute(BaseAttributes.HEALTH) > 0;
        }
    }


    public void setWeapon(IntrinsicWeapon weapon) {
        this.setIntrinsicWeapon(weapon);
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);

        if (sleepTurns > 1) {
            sleepTurns--;
            // While sleeping, skip action (but world still ticks)
            System.out.println(name + " is sleeping for " + sleepTurns + " turns.");
            return new DoNothingAction();
        } else {
            //Decrease hydration and warmth after every turn
            this.decreaseHydration(HYDRATION_DECREASE_VALUE);
            this.decreaseWarmth(WARMTH_DECREASE_VALUE);


            //Print player hydration and warmth
            System.out.println(this.ToString());
            return menu.showMenu(this, display);
        }

    }

    public String ToString() {
        return super.toString() + " has a hydration level of " + this.hydration + " and a warmth level of " + this.warmth;
    }
}